import java.lang.StringBuilder
import java.net.URL
import java.util.*

data class APIArgument(var name: String, val docsType: String, val description: String, val required: Boolean) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is APIArgument) {
            return this.name == other.name
        }

        return false
    }

    val kotlinType: String
        get() {
            return when (docsType) {
                "<string>", "string" -> "String"
                "<bool>", "bool" -> "Boolean"
                "<uint64>", "int64" -> "Long"
                "<int>", "int" -> "Int"
                "array" -> "Array<*>"
                else -> throw RuntimeException("not support type:$docsType")
            }
        }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    fun toKotlinSignatureString(suffix: String?): String {
        return "@Query(\"${this.name}\") ${this.name}$suffix:${this.kotlinType}"
    }

    fun toKotlinDocsString(suffix: String?): String {
        return "* @param ${this.name}$suffix ${this.description}"
    }
}

data class APIResponseType(val name: String, val type: String) {
    companion object {
        val TYPE_STRING = APIResponseType("<string>", "String")
        val TYPE_BOOLEAN = APIResponseType("<bool>", "Boolean")
        val TYPE_INT = APIResponseType("<int>", "Int")
        val TYPE_UINT64 = APIResponseType("<uint64>", "Long")

        val TYPE_VOID = APIResponseType("<void>", "void")
    }

    fun genCode(): String {
        val t = when (this.type) {
            "<string>" -> "String"
            "<bool>" -> "Boolean"
            "<uint64>" -> "Long"
            "<int>" -> "Int"
            else -> throw RuntimeException("not support type:${this.type}")
        }

        return "${this.name}:$t"
    }
}

data class APIResponse(val description: String, val example: String) {
    fun toKotlinDocsString(): String {
        val builder = StringBuilder()
        builder.append("* @return ${this.description}<br><pre>\n")
        builder.append(this.example.lines().joinToString("\n") { "* $it" })
            .append("</pre>")
        return builder.toString()
    }

    val returnType: String
        get() {
            return if (this.example.contains("`text/plain`")) {
                "ResponseBody"
            } else {
                "JSONObject"
            }
        }

    private fun convertDocsType2KotlinType(type: String): String {
        return when (type) {
            "<int>" -> "Int"
            "<uint64>" -> "Long"
            "<string>" -> "String"
            "<bool>" -> "Boolean"
            else -> throw RuntimeException("unsupported docs type $type")
        }
    }
}

data class FinalAPI(
    val endpoint: String,
    val description: String,
    val arguments: List<APIArgument>,
    val response: APIResponse,
    val failure: Boolean
) {
    private val LF = '\n'

    private val mSignatureName: String by lazy {
        val builder = StringBuilder()
        this.endpoint.split('/')
            .forEachIndexed { index, s ->
                val name = s.replace("-", "_")
                if (index == 3) {
                    builder.append(name)
                } else if (index > 3) {
                    builder.append(name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                }
            }

        builder.toString()
    }

    val signatureName: String
        get() = this.mSignatureName

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("/**").append(LF)
            .append("* ").append(this.description).append(LF)

        builder.append(this.arguments.mapIndexed { idx, arg -> arg.toKotlinDocsString("$idx") }.joinToString("\n"))
            .append(LF)


        builder.append(this.response.toKotlinDocsString()).append("*/").append(LF)

        // source code
        builder.append("@POST(\"${this.endpoint}\")").append(LF)
        builder.append("suspend fun ").append(this.signatureName).append('(')
        builder.append(this.arguments.mapIndexed { idx, arg -> arg.toKotlinSignatureString("$idx") }.joinToString(","))
        builder.append(')')
            .append(':')
            .append(this.response.returnType)

        return builder.toString()
    }
}

object Generators {
    private const val FLAG_SKIP = 0
    private const val FLAG_ARGUMENTS = 1
    private const val FLAG_RESPONSE = 2
    private const val FLAG_CURL = 4
    private const val FLAG_DONE = 8
    private const val FLAG_BODY = 16

    private val regexArguments = "^- `(\\w+)` \\[([\\w\\d]+)\\]: (.+)\$".toRegex()
    private val regexResponse = "```json([\\s\\S\\n]+)```".toRegex()

    private fun dispatchFlag(line: String, defaultValue: Int): Int {
        return when (line) {
            "### Arguments" -> FLAG_ARGUMENTS
            "### Response" -> FLAG_RESPONSE
            "### cURL Example" -> FLAG_CURL
            "### Request Body" -> FLAG_BODY
            "---" -> FLAG_DONE
            else -> defaultValue
        }
    }

    private fun processEachAPI(md: Scanner, endpoint: String): FinalAPI {
        val arguments = mutableListOf<APIArgument>()
        var description = ""
        var failure = false
        val response = StringBuilder()

        while (description.isEmpty()) {
            description = md.nextLine()
        }

        var flag = dispatchFlag(description, FLAG_SKIP)

        if (flag != FLAG_SKIP) description = ""

        while (md.hasNextLine()) {
            val line = md.nextLine()
            flag = dispatchFlag(line, flag)

            when (flag) {
                FLAG_ARGUMENTS -> {
                    regexArguments.find(line)?.let {
                        APIArgument(
                            it.groupValues[1],
                            it.groupValues[2],
                            it.groupValues[3],
                            it.groupValues[3].contains("Required: **yes**")
                        ).let(arguments::add)
                    }
                }
                FLAG_RESPONSE -> {
                    response.append(line).append('\n')
                }
                FLAG_CURL -> {}
                FLAG_BODY -> {
                    failure = true
                }
                FLAG_DONE -> {
                    val str = response.toString().replace("### Response", "")
                    val responseBody = regexResponse.find(str)!!.groupValues[1]
                    val responseDescription = str.replace(regexResponse, "").trim()
                        .replace("\r|\n".toRegex(), "")

                    return APIResponse(
                        responseDescription,
                        responseBody
                    ).let { FinalAPI(endpoint, description, arguments, it, failure) }
                }
            }
        }

        throw RuntimeException("process api fails with $endpoint")
    }

    fun generate(docs: String): String {
        val apis = mutableSetOf<FinalAPI>()
        URL(docs).openStream()
            .use { input ->
                val md = Scanner(input)
                while (md.hasNextLine()) {
                    val eachLine = md.nextLine()
                    if (eachLine.startsWith("## /api/")) {
                        val api = processEachAPI(md, eachLine.substring(3))
                        if (api.failure) {
                            System.err.println("Failure: $api")
                        } else {
                            apis.add(api)
                        }
                    }
                }
            }

        return apis.joinToString("\n")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val sourceCode = generate("https://raw.githubusercontent.com/ipfs/ipfs-docs/main/docs/reference/http/api.md")
        println("Source Code:")
        println(sourceCode)
    }
}