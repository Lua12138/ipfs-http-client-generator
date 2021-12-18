import controller.UploaderController
import java.lang.StringBuilder
import java.net.URL
import java.util.*

data class API(
    val endpoint: String,
    val description: String,
    val arguments: List<Argument>,
    val response: Response,
    val failure: Boolean
) {
    data class Argument(var name: String, val type: String, val description: String, val required: Boolean) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other is Argument) {
                return this.name == other.name
            }

            return false
        }

        override fun hashCode(): Int {
            return name.hashCode()
        }
    }

    data class Response(val description: String, val example: String)
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

    private fun processEachAPI(md: Scanner, name: String): API {
        val arguments = mutableListOf<API.Argument>()

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
                        API.Argument(
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

                    return API(
                        name, description, arguments,
                        API.Response(
                            responseDescription.replace("\r|\n".toRegex(), ""),
                            responseBody
                        ), failure
                    )
                }
            }
        }

        throw RuntimeException("process api fails with $name")
    }

    private fun convertType(type: String): String {
        return when (type) {
            "string" -> "String"
            "bool" -> "Boolean"
            "int64" -> "Long"
            "int" -> "Int"
            "array" -> "Array<*>"
            else -> throw RuntimeException("not support type:$type")
        }
    }

    /**
     * @param apis aaaa
     * @return adsfdsa
     */
    private fun makeSuspendFun(apis: Set<API>): String {
        val LF = '\n'
        return apis.joinToString("\n") { api ->
            val codeBuilder = StringBuilder()
            val commentBuilder = StringBuilder()
            // comment
            commentBuilder.append("/**").append(LF)
            commentBuilder.append("* ").append(api.description).append(LF)
            // argument comment

            codeBuilder.append("@POST(\"${api.endpoint}\")").append(LF)
            codeBuilder.append("suspend fun ")

            // name
            api.endpoint.split('/')
                .forEachIndexed { index, s ->
                    val name = s.replace("-", "_")
                    if (index == 3) {
                        codeBuilder.append(name)
                    } else if (index > 3) {
                        codeBuilder.append(name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                    }
                }

            // arguments
            codeBuilder.append('(')
            api.arguments.forEachIndexed { idx, argument ->
                commentBuilder.append("* @param ")
                    .append(argument.name).append(idx).append(' ')
                    .append(argument.description)
                    .append(LF)

                codeBuilder.append("@Path(\"${argument.name}\")")
                    // Avoid duplicate parameter names
                    .append(argument.name).append(idx)
                    .append(':')
                    .append(convertType(argument.type))
                    .append(',')
            }
            // remove last cahr ','
            if (api.arguments.isNotEmpty()) {
                codeBuilder.deleteAt(codeBuilder.length - 1)
            }
            codeBuilder.append(')')

            // return type
            codeBuilder.append(": JSONObject")

            commentBuilder.append("* @return ")
                .append(api.response.description).append(LF)
                .append("* <br>Example Response:<pre>").append(LF)
            api.response.example.split('\n').forEach { line ->
                commentBuilder.append("* ").append(line).append(LF)
            }
            commentBuilder.append("* </pre>").append(LF)
            commentBuilder.append("*/").append(LF)

            "$commentBuilder\n$codeBuilder"
        }
    }

    fun generate(docs: String): String {
        val apis = mutableSetOf<API>()
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

        return makeSuspendFun(apis)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val sourceCode = generate("https://raw.githubusercontent.com/ipfs/ipfs-docs/main/docs/reference/http/api.md")
        println("Source Code:")
        println(sourceCode)
    }
}