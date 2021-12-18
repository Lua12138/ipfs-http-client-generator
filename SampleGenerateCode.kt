import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.POST
import retrofit2.http.Query

interface IPFS {
    /**
     * Show the current ledger for a peer.
     * @param arg0 The PeerID (B58) of the ledger to inspect. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Exchanged": "<uint64>",
     *   "Peer": "<string>",
     *   "Recv": "<uint64>",
     *   "Sent": "<uint64>",
     *   "Value": "<float64>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/bitswap/ledger")
    suspend fun bitswapLedger(@Query("arg") arg0: String): JSONObject

    /**
     * Trigger reprovider.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/bitswap/reprovide")
    suspend fun bitswapReprovide(): ResponseBody

    /**
     * Show some diagnostic information on the bitswap agent.
     * @param verbose0 Print extra information. Required: no.
     * @param human1 Print sizes in human readable format (e.g., 1K 234M 2G). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "BlocksReceived": "<uint64>",
     *   "BlocksSent": "<uint64>",
     *   "DataReceived": "<uint64>",
     *   "DataSent": "<uint64>",
     *   "DupBlksReceived": "<uint64>",
     *   "DupDataReceived": "<uint64>",
     *   "MessagesReceived": "<uint64>",
     *   "Peers": [
     *     "<string>"
     *   ],
     *   "ProvideBufLen": "<int>",
     *   "Wantlist": [
     *     {
     *       "/": "<cid-string>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/bitswap/stat")
    suspend fun bitswapStat(@Query("verbose") verbose0: Boolean, @Query("human") human1: Boolean): JSONObject

    /**
     * Show blocks currently on the wantlist.
     * @param peer0 Specify which peer to show wantlist for. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Keys": [
     *     {
     *       "/": "<cid-string>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/bitswap/wantlist")
    suspend fun bitswapWantlist(@Query("peer") peer0: String): JSONObject

    /**
     * Get a raw IPFS block.
     * @param arg0 The base58 multihash of an existing block to get. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/block/get")
    suspend fun blockGet(@Query("arg") arg0: String): ResponseBody

    /**
     * Remove IPFS block(s).
     * @param arg0 Bash58 encoded multihash of block(s) to remove. Required: **yes**.
     * @param force1 Ignore nonexistent blocks. Required: no.
     * @param quiet2 Write minimal output. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Error": "<string>",
     *   "Hash": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/block/rm")
    suspend fun blockRm(
        @Query("arg") arg0: String,
        @Query("force") force1: Boolean,
        @Query("quiet") quiet2: Boolean
    ): JSONObject

    /**
     * Print information of a raw IPFS block.
     * @param arg0 The base58 multihash of an existing block to stat. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Key": "<string>",
     *   "Size": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/block/stat")
    suspend fun blockStat(@Query("arg") arg0: String): JSONObject

    /**
     * Show or edit the list of bootstrap peers.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Peers": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/bootstrap")
    suspend fun bootstrap(): JSONObject

    /**
     * Add peers to the bootstrap list.
     * @param arg0 A peer to add to the bootstrap list (in the format &#39;&lt;multiaddr&gt;/&lt;peerID&gt;&#39;) Required: no.
     * @param default1 Add default bootstrap nodes. (Deprecated, use &#39;default&#39; subcommand instead). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Peers": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/bootstrap/add")
    suspend fun bootstrapAdd(@Query("arg") arg0: String, @Query("default") default1: Boolean): JSONObject

    /**
     * Add default peers to the bootstrap list.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Peers": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/bootstrap/add/default")
    suspend fun bootstrapAddDefault(): JSONObject

    /**
     * Show peers in the bootstrap list.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Peers": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/bootstrap/list")
    suspend fun bootstrapList(): JSONObject

    /**
     * Remove peers from the bootstrap list.
     * @param arg0 A peer to add to the bootstrap list (in the format &#39;&lt;multiaddr&gt;/&lt;peerID&gt;&#39;) Required: no.
     * @param all1 Remove all bootstrap peers. (Deprecated, use &#39;all&#39; subcommand). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Peers": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/bootstrap/rm")
    suspend fun bootstrapRm(@Query("arg") arg0: String, @Query("all") all1: Boolean): JSONObject

    /**
     * Remove all peers from the bootstrap list.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Peers": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/bootstrap/rm/all")
    suspend fun bootstrapRmAll(): JSONObject

    /**
     * Show IPFS object data.
     * @param arg0 The path to the IPFS object(s) to be outputted. Required: **yes**.
     * @param offset1 Byte offset to begin reading from. Required: no.
     * @param length2 Maximum number of bytes to read. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/cat")
    suspend fun cat(
        @Query("arg") arg0: String,
        @Query("offset") offset1: Long,
        @Query("length") length2: Long
    ): ResponseBody

    /**
     * Convert CIDs to Base32 CID version 1.
     * @param arg0 Cids to convert. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "CidStr": "<string>",
     *   "ErrorMsg": "<string>",
     *   "Formatted": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/cid/base32")
    suspend fun cidBase32(@Query("arg") arg0: String): JSONObject

    /**
     * List available multibase encodings.
     * @param prefix0 also include the single letter prefixes in addition to the code. Required: no.
     * @param numeric1 also include numeric codes. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * [
     *   {
     *     "Code": "<int>",
     *     "Name": "<string>"
     *   }
     * ]
     *
     * </pre>*/
    @POST("/api/v0/cid/bases")
    suspend fun cidBases(@Query("prefix") prefix0: Boolean, @Query("numeric") numeric1: Boolean): JSONObject

    /**
     * List available CID codecs.
     * @param numeric0 also include numeric codes. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * [
     *   {
     *     "Code": "<int>",
     *     "Name": "<string>"
     *   }
     * ]
     *
     * </pre>*/
    @POST("/api/v0/cid/codecs")
    suspend fun cidCodecs(@Query("numeric") numeric0: Boolean): JSONObject

    /**
     * Format and convert a CID in various useful ways.
     * @param arg0 Cids to format. Required: **yes**.
     * @param f1 Printf style format string. Default: %s. Default: `%s`. Required: no.
     * @param v2 CID version to convert to. Required: no.
     * @param codec3 CID codec to convert to. Required: no.
     * @param b4 Multibase to display CID in. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "CidStr": "<string>",
     *   "ErrorMsg": "<string>",
     *   "Formatted": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/cid/format")
    suspend fun cidFormat(
        @Query("arg") arg0: String,
        @Query("f") f1: String,
        @Query("v") v2: String,
        @Query("codec") codec3: String,
        @Query("b") b4: String
    ): JSONObject

    /**
     * List available multihashes.
     * @param numeric0 also include numeric codes. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * [
     *   {
     *     "Code": "<int>",
     *     "Name": "<string>"
     *   }
     * ]
     *
     * </pre>*/
    @POST("/api/v0/cid/hashes")
    suspend fun cidHashes(@Query("numeric") numeric0: Boolean): JSONObject

    /**
     * List all available commands.
     * @param flags0 Show command flags. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Name": "<string>",
     *   "Options": [
     *     {
     *       "Names": [
     *         "<string>"
     *       ]
     *     }
     *   ],
     *   "Subcommands": [
     *     {
     *       "Name": "<string>",
     *       "Options": [
     *         {
     *           "Names": [
     *             "<string>"
     *           ]
     *         }
     *       ],
     *       "Subcommands": [
     *         "..."
     *       ]
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/commands")
    suspend fun commands(@Query("flags") flags0: Boolean): JSONObject

    /**
     * Generate bash shell completions.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/commands/completion/bash")
    suspend fun commandsCompletionBash(): ResponseBody

    /**
     * Get and set IPFS config values.
     * @param arg0 The key of the config entry (e.g. &#34;Addresses.API&#34;). Required: **yes**.
     * @param arg1 The value to set the config entry to. Required: no.
     * @param bool2 Set a boolean value. Required: no.
     * @param json3 Parse stringified JSON. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Key": "<string>",
     *   "Value": "<object>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/config")
    suspend fun config(
        @Query("arg") arg0: String,
        @Query("arg") arg1: String,
        @Query("bool") bool2: Boolean,
        @Query("json") json3: Boolean
    ): JSONObject

    /**
     * Open the config file for editing in $EDITOR.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/config/edit")
    suspend fun configEdit(): ResponseBody

    /**
     * Apply profile to config.
     * @param arg0 The profile to apply to the config. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "NewCfg": {
     *     "<string>": "<object>"
     *   },
     *   "OldCfg": {
     *     "<string>": "<object>"
     *   }
     * }
     *
     * </pre>*/
    @POST("/api/v0/config/profile/apply")
    suspend fun configProfileApply(@Query("arg") arg0: String): JSONObject

    /**
     * Output config file contents.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "<string>": "<object>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/config/show")
    suspend fun configShow(): JSONObject

    /**
     * Streams the selected DAG as a .car stream on stdout.
     * @param arg0 CID of a root to recursively export Required: **yes**.
     * @param progress1 Display progress on CLI. Defaults to true when STDERR is a TTY. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/dag/export")
    suspend fun dagExport(@Query("arg") arg0: String, @Query("progress") progress1: Boolean): ResponseBody

    /**
     * Get a DAG node from IPFS.
     * @param arg0 The object to get Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/dag/get")
    suspend fun dagGet(@Query("arg") arg0: String): ResponseBody

    /**
     * Resolve IPLD block.
     * @param arg0 The path to resolve Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Cid": {
     *     "/": "<cid-string>"
     *   },
     *   "RemPath": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/dag/resolve")
    suspend fun dagResolve(@Query("arg") arg0: String): JSONObject

    /**
     * Gets stats for a DAG.
     * @param arg0 CID of a DAG root to get statistics for Required: **yes**.
     * @param progress1 Return progressive data while reading through the DAG. Default: `true`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "NumBlocks": "<int64>",
     *   "Size": "<uint64>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/dag/stat")
    suspend fun dagStat(@Query("arg") arg0: String, @Query("progress") progress1: Boolean): JSONObject

    /**
     * Find the multiaddresses associated with a Peer ID.
     * @param arg0 The ID of the peer to search for. Required: **yes**.
     * @param verbose1 Print extra information. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Extra": "<string>",
     *   "ID": "<peer-id>",
     *   "Responses": [
     *     {
     *       "Addrs": [
     *         "<multiaddr-string>"
     *       ],
     *       "ID": "peer-id"
     *     }
     *   ],
     *   "Type": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/dht/findpeer")
    suspend fun dhtFindpeer(@Query("arg") arg0: String, @Query("verbose") verbose1: Boolean): JSONObject

    /**
     * Find peers that can provide a specific value, given a key.
     * @param arg0 The key to find providers for. Required: **yes**.
     * @param verbose1 Print extra information. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Extra": "<string>",
     *   "ID": "<peer-id>",
     *   "Responses": [
     *     {
     *       "Addrs": [
     *         "<multiaddr-string>"
     *       ],
     *       "ID": "peer-id"
     *     }
     *   ],
     *   "Type": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/dht/findprovs")
    suspend fun dhtFindprovs(@Query("arg") arg0: String, @Query("verbose") verbose1: Boolean): JSONObject

    /**
     * Given a key, query the routing system for its best value.
     * @param arg0 The key to find a value for. Required: **yes**.
     * @param verbose1 Print extra information. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Extra": "<string>",
     *   "ID": "<peer-id>",
     *   "Responses": [
     *     {
     *       "Addrs": [
     *         "<multiaddr-string>"
     *       ],
     *       "ID": "peer-id"
     *     }
     *   ],
     *   "Type": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/dht/get")
    suspend fun dhtGet(@Query("arg") arg0: String, @Query("verbose") verbose1: Boolean): JSONObject

    /**
     * Announce to the network that you are providing given values.
     * @param arg0 The key[s] to send provide records for. Required: **yes**.
     * @param verbose1 Print extra information. Required: no.
     * @param recursive2 Recursively provide entire graph. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Extra": "<string>",
     *   "ID": "<peer-id>",
     *   "Responses": [
     *     {
     *       "Addrs": [
     *         "<multiaddr-string>"
     *       ],
     *       "ID": "peer-id"
     *     }
     *   ],
     *   "Type": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/dht/provide")
    suspend fun dhtProvide(
        @Query("arg") arg0: String,
        @Query("verbose") verbose1: Boolean,
        @Query("recursive") recursive2: Boolean
    ): JSONObject

    /**
     * Find the closest Peer IDs to a given Peer ID by querying the DHT.
     * @param arg0 The peerID to run the query against. Required: **yes**.
     * @param verbose1 Print extra information. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Extra": "<string>",
     *   "ID": "<peer-id>",
     *   "Responses": [
     *     {
     *       "Addrs": [
     *         "<multiaddr-string>"
     *       ],
     *       "ID": "peer-id"
     *     }
     *   ],
     *   "Type": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/dht/query")
    suspend fun dhtQuery(@Query("arg") arg0: String, @Query("verbose") verbose1: Boolean): JSONObject

    /**
     * List commands run on this IPFS node.
     * @param verbose0 Print extra information. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * [
     *   {
     *     "Active": "<bool>",
     *     "Args": [
     *       "<string>"
     *     ],
     *     "Command": "<string>",
     *     "EndTime": "<timestamp>",
     *     "ID": "<int>",
     *     "Options": {
     *       "<string>": "<object>"
     *     },
     *     "StartTime": "<timestamp>"
     *   }
     * ]
     *
     * </pre>*/
    @POST("/api/v0/diag/cmds")
    suspend fun diagCmds(@Query("verbose") verbose0: Boolean): JSONObject

    /**
     * Clear inactive requests from the log.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/diag/cmds/clear")
    suspend fun diagCmdsClear(): ResponseBody

    /**
     * Set how long to keep inactive requests in the log.
     * @param arg0 Time to keep inactive requests in log. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/diag/cmds/set-time")
    suspend fun diagCmdsSet_time(@Query("arg") arg0: String): ResponseBody

    /**
     * Collect a performance profile for debugging.
     * @param output0 The path where the output should be stored. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/diag/profile")
    suspend fun diagProfile(@Query("output") output0: String): ResponseBody

    /**
     * Print system diagnostic information.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/diag/sys")
    suspend fun diagSys(): ResponseBody

    /**
     * Resolve DNS links.
     * @param arg0 The domain-name name to resolve. Required: **yes**.
     * @param recursive1 Resolve until the result is not a DNS link. Default: `true`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Path": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/dns")
    suspend fun dns(@Query("arg") arg0: String, @Query("recursive") recursive1: Boolean): JSONObject

    /**
     * List directory contents for Unix filesystem objects. Deprecated: Use &#39;ipfs ls&#39; instead.
     * @param arg0 The path to the IPFS object(s) to list links from. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Arguments": {
     *     "<string>": "<string>"
     *   },
     *   "Objects": {
     *     "<string>": {
     *       "Hash": "<string>",
     *       "Links": [
     *         {
     *           "Hash": "<string>",
     *           "Name": "<string>",
     *           "Size": "<uint64>",
     *           "Type": "<string>"
     *         }
     *       ],
     *       "Size": "<uint64>",
     *       "Type": "<string>"
     *     }
     *   }
     * }
     *
     * </pre>*/
    @POST("/api/v0/file/ls")
    suspend fun fileLs(@Query("arg") arg0: String): JSONObject

    /**
     * Change the CID version or hash function of the root node of a given path.
     * @param arg0 Path to change. Default: &#39;/&#39;. Required: no.
     * @param hash1 Hash function to use. Will set Cid version to 1 if used. (experimental). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/files/chcid")
    suspend fun filesChcid(@Query("arg") arg0: String, @Query("hash") hash1: String): ResponseBody

    /**
     * Add references to IPFS files and directories in MFS (or copy within MFS).
     * @param arg0 Source IPFS or MFS path to copy. Required: **yes**.
     * @param arg1 Destination within MFS. Required: **yes**.
     * @param parents2 Make parent directories as needed. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/files/cp")
    suspend fun filesCp(
        @Query("arg") arg0: String,
        @Query("arg") arg1: String,
        @Query("parents") parents2: Boolean
    ): ResponseBody

    /**
     * Flush a given path&#39;s data to disk.
     * @param arg0 Path to flush. Default: &#39;/&#39;. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Cid": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/files/flush")
    suspend fun filesFlush(@Query("arg") arg0: String): JSONObject

    /**
     * List directories in the local mutable namespace.
     * @param arg0 Path to show listing for. Defaults to &#39;/&#39;. Required: no.
     * @param long1 Use long listing format. Required: no.
     * @param U2 Do not sort; list entries in directory order. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Entries": [
     *     {
     *       "Hash": "<string>",
     *       "Name": "<string>",
     *       "Size": "<int64>",
     *       "Type": "<int>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/files/ls")
    suspend fun filesLs(@Query("arg") arg0: String, @Query("long") long1: Boolean, @Query("U") U2: Boolean): JSONObject

    /**
     * Make directories.
     * @param arg0 Path to dir to make. Required: **yes**.
     * @param parents1 No error if existing, make parent directories as needed. Required: no.
     * @param hash2 Hash function to use. Will set Cid version to 1 if used. (experimental). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/files/mkdir")
    suspend fun filesMkdir(
        @Query("arg") arg0: String,
        @Query("parents") parents1: Boolean,
        @Query("hash") hash2: String
    ): ResponseBody

    /**
     * Move files.
     * @param arg0 Source file to move. Required: **yes**.
     * @param arg1 Destination path for file to be moved to. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/files/mv")
    suspend fun filesMv(@Query("arg") arg0: String, @Query("arg") arg1: String): ResponseBody

    /**
     * Read a file in a given MFS.
     * @param arg0 Path to file to be read. Required: **yes**.
     * @param offset1 Byte offset to begin reading from. Required: no.
     * @param count2 Maximum number of bytes to read. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/files/read")
    suspend fun filesRead(
        @Query("arg") arg0: String,
        @Query("offset") offset1: Long,
        @Query("count") count2: Long
    ): ResponseBody

    /**
     * Remove a file.
     * @param arg0 File to remove. Required: **yes**.
     * @param recursive1 Recursively remove directories. Required: no.
     * @param force2 Forcibly remove target at path; implies -r for directories. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/files/rm")
    suspend fun filesRm(
        @Query("arg") arg0: String,
        @Query("recursive") recursive1: Boolean,
        @Query("force") force2: Boolean
    ): ResponseBody

    /**
     * Display file status.
     * @param arg0 Path to node to stat. Required: **yes**.
     * @param format1 Print statistics in given format. Allowed tokens: &lt;hash&gt; &lt;size&gt; &lt;cumulsize&gt; &lt;type&gt; &lt;childs&gt;. Conflicts with other format options. Default: &lt;hash&gt;
     * @param hash2 Print only hash. Implies &#39;--format=&lt;hash&gt;&#39;. Conflicts with other format options. Required: no.
     * @param size3 Print only size. Implies &#39;--format=&lt;cumulsize&gt;&#39;. Conflicts with other format options. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Blocks": "<int>",
     *   "CumulativeSize": "<uint64>",
     *   "Hash": "<string>",
     *   "Local": "<bool>",
     *   "Size": "<uint64>",
     *   "SizeLocal": "<uint64>",
     *   "Type": "<string>",
     *   "WithLocality": "<bool>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/files/stat")
    suspend fun filesStat(
        @Query("arg") arg0: String,
        @Query("format") format1: String,
        @Query("hash") hash2: Boolean,
        @Query("size") size3: Boolean
    ): JSONObject

    /**
     * List blocks that are both in the filestore and standard block storage.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Err": "<string>",
     *   "Ref": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/filestore/dups")
    suspend fun filestoreDups(): JSONObject

    /**
     * List objects in filestore.
     * @param arg0 Cid of objects to list. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "ErrorMsg": "<string>",
     *   "FilePath": "<string>",
     *   "Key": {
     *     "/": "<cid-string>"
     *   },
     *   "Offset": "<uint64>",
     *   "Size": "<uint64>",
     *   "Status": "<int32>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/filestore/ls")
    suspend fun filestoreLs(@Query("arg") arg0: String): JSONObject

    /**
     * Verify objects in filestore.
     * @param arg0 Cid of objects to verify. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "ErrorMsg": "<string>",
     *   "FilePath": "<string>",
     *   "Key": {
     *     "/": "<cid-string>"
     *   },
     *   "Offset": "<uint64>",
     *   "Size": "<uint64>",
     *   "Status": "<int32>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/filestore/verify")
    suspend fun filestoreVerify(@Query("arg") arg0: String): JSONObject

    /**
     * Download IPFS objects.
     * @param arg0 The path to the IPFS object(s) to be outputted. Required: **yes**.
     * @param output1 The path where the output should be stored. Required: no.
     * @param archive2 Output a TAR archive. Required: no.
     * @param compress3 Compress the output with GZIP compression. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/get")
    suspend fun get(
        @Query("arg") arg0: String,
        @Query("output") output1: String,
        @Query("archive") archive2: Boolean,
        @Query("compress") compress3: Boolean
    ): ResponseBody

    /**
     * Show IPFS node id info.
     * @param arg0 Peer.ID of node to look up. Required: no.
     * @param format1 Optional output format. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Addresses": [
     *     "<string>"
     *   ],
     *   "AgentVersion": "<string>",
     *   "ID": "<string>",
     *   "ProtocolVersion": "<string>",
     *   "Protocols": [
     *     "<string>"
     *   ],
     *   "PublicKey": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/id")
    suspend fun id(@Query("arg") arg0: String, @Query("format") format1: String): JSONObject

    /**
     * Export a keypair
     * @param arg0 name of key to export Required: **yes**.
     * @param output1 The path where the output should be stored. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/key/export")
    suspend fun keyExport(@Query("arg") arg0: String, @Query("output") output1: String): ResponseBody

    /**
     * Create a new keypair
     * @param arg0 name of key to create Required: **yes**.
     * @param type1 type of the key to create: rsa, ed25519. Default: `ed25519`. Required: no.
     * @param size2 size of the key to generate. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Id": "<string>",
     *   "Name": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/key/gen")
    suspend fun keyGen(@Query("arg") arg0: String, @Query("type") type1: String, @Query("size") size2: Int): JSONObject

    /**
     * List all local keypairs.
     * @param l0 Show extra information about keys. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Keys": [
     *     {
     *       "Id": "<string>",
     *       "Name": "<string>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/key/list")
    suspend fun keyList(@Query("l") l0: Boolean): JSONObject

    /**
     * Rename a keypair.
     * @param arg0 name of key to rename Required: **yes**.
     * @param arg1 new name of the key Required: **yes**.
     * @param force2 Allow to overwrite an existing key. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Id": "<string>",
     *   "Now": "<string>",
     *   "Overwrite": "<bool>",
     *   "Was": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/key/rename")
    suspend fun keyRename(
        @Query("arg") arg0: String,
        @Query("arg") arg1: String,
        @Query("force") force2: Boolean
    ): JSONObject

    /**
     * Remove a keypair.
     * @param arg0 names of keys to remove Required: **yes**.
     * @param l1 Show extra information about keys. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Keys": [
     *     {
     *       "Id": "<string>",
     *       "Name": "<string>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/key/rm")
    suspend fun keyRm(@Query("arg") arg0: String, @Query("l") l1: Boolean): JSONObject

    /**
     * Rotates the IPFS identity.
     * @param oldkey0 Keystore name to use for backing up your existing identity. Required: no.
     * @param type1 type of the key to create: rsa, ed25519. Default: `ed25519`. Required: no.
     * @param size2 size of the key to generate. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/key/rotate")
    suspend fun keyRotate(
        @Query("oldkey") oldkey0: String,
        @Query("type") type1: String,
        @Query("size") size2: Int
    ): ResponseBody

    /**
     * Change the logging level.
     * @param arg0 The subsystem logging identifier. Use &#39;all&#39; for all subsystems. Required: **yes**.
     * @param arg1 The log level, with &#39;debug&#39; the most verbose and &#39;fatal&#39; the least verbose.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Message": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/log/level")
    suspend fun logLevel(@Query("arg") arg0: String, @Query("arg") arg1: String): JSONObject

    /**
     * List the logging subsystems.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/log/ls")
    suspend fun logLs(): JSONObject

    /**
     * Read the event log.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/log/tail")
    suspend fun logTail(): ResponseBody

    /**
     * List directory contents for Unix filesystem objects.
     * @param arg0 The path to the IPFS object(s) to list links from. Required: **yes**.
     * @param headers1 Print table headers (Hash, Size, Name). Required: no.
     * @param size2 Resolve linked objects to find out their file size. Default: `true`. Required: no.
     * @param stream3 Enable experimental streaming of directory entries as they are traversed. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Objects": [
     *     {
     *       "Hash": "<string>",
     *       "Links": [
     *         {
     *           "Hash": "<string>",
     *           "Name": "<string>",
     *           "Size": "<uint64>",
     *           "Target": "<string>",
     *           "Type": "<int32>"
     *         }
     *       ]
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/ls")
    suspend fun ls(
        @Query("arg") arg0: String,
        @Query("headers") headers1: Boolean,
        @Query("size") size2: Boolean,
        @Query("stream") stream3: Boolean
    ): JSONObject

    /**
     * Mounts IPFS to the filesystem (read-only).

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "FuseAllowOther": "<bool>",
     *   "IPFS": "<string>",
     *   "IPNS": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/mount")
    suspend fun mount(): JSONObject

    /**
     * List available multibase encodings.
     * @param prefix0 also include the single letter prefixes in addition to the code. Required: no.
     * @param numeric1 also include numeric codes. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * [
     *   {
     *     "Code": "<int>",
     *     "Name": "<string>"
     *   }
     * ]
     *
     * </pre>*/
    @POST("/api/v0/multibase/list")
    suspend fun multibaseList(@Query("prefix") prefix0: Boolean, @Query("numeric") numeric1: Boolean): JSONObject

    /**
     * Publish IPNS names.
     * @param arg0 ipfs path of the object to be published. Required: **yes**.
     * @param resolve1 Check if the given path can be resolved before publishing. Default: `true`. Required: no.
     * @param lifetime2 Time duration that the record will be valid for.
     * @param ttl3 Time duration this record should be cached for. Uses the same syntax as the lifetime option. (caution: experimental). Required: no.
     * @param key4 Name of the key to be used or a valid PeerID, as listed by &#39;ipfs key list -l&#39;. Default: `self`. Required: no.
     * @param quieter5 Write only final hash. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Name": "<string>",
     *   "Value": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/name/publish")
    suspend fun namePublish(
        @Query("arg") arg0: String,
        @Query("resolve") resolve1: Boolean,
        @Query("lifetime") lifetime2: String,
        @Query("ttl") ttl3: String,
        @Query("key") key4: String,
        @Query("quieter") quieter5: Boolean
    ): JSONObject

    /**
     * Cancel a name subscription.
     * @param arg0 Name to cancel the subscription for. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Canceled": "<bool>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/name/pubsub/cancel")
    suspend fun namePubsubCancel(@Query("arg") arg0: String): JSONObject

    /**
     * Query the state of IPNS pubsub.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Enabled": "<bool>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/name/pubsub/state")
    suspend fun namePubsubState(): JSONObject

    /**
     * Show current name subscriptions.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/name/pubsub/subs")
    suspend fun namePubsubSubs(): JSONObject

    /**
     * Resolve IPNS names.
     * @param arg0 The IPNS name to resolve. Defaults to your node&#39;s peerID. Required: no.
     * @param recursive1 Resolve until the result is not an IPNS name. Default: `true`. Required: no.
     * @param nocache2 Do not use cached entries. Required: no.
     * @param stream3 Stream entries as they are found. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Path": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/name/resolve")
    suspend fun nameResolve(
        @Query("arg") arg0: String,
        @Query("recursive") recursive1: Boolean,
        @Query("nocache") nocache2: Boolean,
        @Query("stream") stream3: Boolean
    ): JSONObject

    /**
     * Deprecated way to read the raw bytes of a dag-pb object: use &#39;dag get&#39; instead.
     * @param arg0 Key of the object to retrieve, in base58-encoded multihash format. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/object/data")
    suspend fun objectData(@Query("arg") arg0: String): ResponseBody

    /**
     * Display the diff between two IPFS objects.
     * @param arg0 Object to diff against. Required: **yes**.
     * @param arg1 Object to diff. Required: **yes**.
     * @param verbose2 Print extra information. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Changes": [
     *     {
     *       "After": {
     *         "/": "<cid-string>"
     *       },
     *       "Before": {
     *         "/": "<cid-string>"
     *       },
     *       "Path": "<string>",
     *       "Type": "<int>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/object/diff")
    suspend fun objectDiff(
        @Query("arg") arg0: String,
        @Query("arg") arg1: String,
        @Query("verbose") verbose2: Boolean
    ): JSONObject

    /**
     * Deprecated way to get and serialize the dag-pb node. Use &#39;dag get&#39; instead
     * @param arg0 Key of the dag-pb object to retrieve, in base58-encoded multihash format. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Data": "<string>",
     *   "Links": [
     *     {
     *       "Hash": "<string>",
     *       "Name": "<string>",
     *       "Size": "<uint64>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/object/get")
    suspend fun objectGet(@Query("arg") arg0: String): JSONObject

    /**
     * Deprecated way to output links in the specified dag-pb object: use &#39;dag get&#39; instead.
     * @param arg0 Key of the dag-pb object to retrieve, in base58-encoded multihash format. Required: **yes**.
     * @param headers1 Print table headers (Hash, Size, Name). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Hash": "<string>",
     *   "Links": [
     *     {
     *       "Hash": "<string>",
     *       "Name": "<string>",
     *       "Size": "<uint64>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/object/links")
    suspend fun objectLinks(@Query("arg") arg0: String, @Query("headers") headers1: Boolean): JSONObject

    /**
     * Deprecated way to create a new dag-pb object from a template.
     * @param arg0 Template to use. Optional. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Hash": "<string>",
     *   "Links": [
     *     {
     *       "Hash": "<string>",
     *       "Name": "<string>",
     *       "Size": "<uint64>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/object/new")
    suspend fun objectNew(@Query("arg") arg0: String): JSONObject

    /**
     * Deprecated way to add a link to a given dag-pb.
     * @param arg0 The hash of the node to modify. Required: **yes**.
     * @param arg1 Name of link to create. Required: **yes**.
     * @param arg2 IPFS object to add link to. Required: **yes**.
     * @param create3 Create intermediary nodes. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Hash": "<string>",
     *   "Links": [
     *     {
     *       "Hash": "<string>",
     *       "Name": "<string>",
     *       "Size": "<uint64>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/object/patch/add-link")
    suspend fun objectPatchAdd_link(
        @Query("arg") arg0: String,
        @Query("arg") arg1: String,
        @Query("arg") arg2: String,
        @Query("create") create3: Boolean
    ): JSONObject

    /**
     * Deprecated way to remove a link from dag-pb object.
     * @param arg0 The hash of the node to modify. Required: **yes**.
     * @param arg1 Name of the link to remove. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Hash": "<string>",
     *   "Links": [
     *     {
     *       "Hash": "<string>",
     *       "Name": "<string>",
     *       "Size": "<uint64>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/object/patch/rm-link")
    suspend fun objectPatchRm_link(@Query("arg") arg0: String, @Query("arg") arg1: String): JSONObject

    /**
     * Deprecated way to read stats for the dag-pb node. Use &#39;files stat&#39; instead.
     * @param arg0 Key of the object to retrieve, in base58-encoded multihash format. Required: **yes**.
     * @param human1 Print sizes in human readable format (e.g., 1K 234M 2G). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "BlockSize": "<int>",
     *   "CumulativeSize": "<int>",
     *   "DataSize": "<int>",
     *   "Hash": "<string>",
     *   "LinksSize": "<int>",
     *   "NumLinks": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/object/stat")
    suspend fun objectStat(@Query("arg") arg0: String, @Query("human") human1: Boolean): JSONObject

    /**
     * Stop listening for new connections to forward.
     * @param all0 Close all listeners. Required: no.
     * @param protocol1 Match protocol name. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * "<int>"
     *
     * </pre>*/
    @POST("/api/v0/p2p/close")
    suspend fun p2pClose(@Query("all") all0: Boolean, @Query("protocol") protocol1: String): JSONObject

    /**
     * Forward connections to libp2p service.
     * @param arg0 Protocol name. Required: **yes**.
     * @param arg1 Listening endpoint. Required: **yes**.
     * @param arg2 Target endpoint. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/p2p/forward")
    suspend fun p2pForward(
        @Query("arg") arg0: String,
        @Query("arg") arg1: String,
        @Query("arg") arg2: String
    ): ResponseBody

    /**
     * Create libp2p service.
     * @param arg0 Protocol name. Required: **yes**.
     * @param arg1 Target endpoint. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/p2p/listen")
    suspend fun p2pListen(@Query("arg") arg0: String, @Query("arg") arg1: String): ResponseBody

    /**
     * List active p2p listeners.
     * @param headers0 Print table headers (Protocol, Listen, Target). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Listeners": [
     *     {
     *       "ListenAddress": "<string>",
     *       "Protocol": "<string>",
     *       "TargetAddress": "<string>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/p2p/ls")
    suspend fun p2pLs(@Query("headers") headers0: Boolean): JSONObject

    /**
     * Close active p2p stream.
     * @param arg0 Stream identifier Required: no.
     * @param all1 Close all streams. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/p2p/stream/close")
    suspend fun p2pStreamClose(@Query("arg") arg0: String, @Query("all") all1: Boolean): ResponseBody

    /**
     * List active p2p streams.
     * @param headers0 Print table headers (ID, Protocol, Local, Remote). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Streams": [
     *     {
     *       "HandlerID": "<string>",
     *       "OriginAddress": "<string>",
     *       "Protocol": "<string>",
     *       "TargetAddress": "<string>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/p2p/stream/ls")
    suspend fun p2pStreamLs(@Query("headers") headers0: Boolean): JSONObject

    /**
     * Pin objects to local storage.
     * @param arg0 Path to object(s) to be pinned. Required: **yes**.
     * @param recursive1 Recursively pin the object linked to by the specified object(s). Default: `true`. Required: no.
     * @param progress2 Show progress. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Pins": [
     *     "<string>"
     *   ],
     *   "Progress": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/pin/add")
    suspend fun pinAdd(
        @Query("arg") arg0: String,
        @Query("recursive") recursive1: Boolean,
        @Query("progress") progress2: Boolean
    ): JSONObject

    /**
     * List objects pinned to local storage.
     * @param arg0 Path to object(s) to be listed. Required: no.
     * @param type1 The type of pinned keys to list. Can be &#34;direct&#34;, &#34;indirect&#34;, &#34;recursive&#34;, or &#34;all&#34;. Default: `all`. Required: no.
     * @param quiet2 Write just hashes of objects. Required: no.
     * @param stream3 Enable streaming of pins as they are discovered. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "PinLsList": {
     *     "Keys": {
     *       "<string>": {
     *         "Type": "<string>"
     *       }
     *     }
     *   },
     *   "PinLsObject": {
     *     "Cid": "<string>",
     *     "Type": "<string>"
     *   }
     * }
     *
     * </pre>*/
    @POST("/api/v0/pin/ls")
    suspend fun pinLs(
        @Query("arg") arg0: String,
        @Query("type") type1: String,
        @Query("quiet") quiet2: Boolean,
        @Query("stream") stream3: Boolean
    ): JSONObject

    /**
     * Pin object to remote pinning service.
     * @param arg0 Path to object(s) to be pinned. Required: **yes**.
     * @param service1 Name of the remote pinning service to use (mandatory). Required: no.
     * @param name2 An optional name for the pin. Required: no.
     * @param background3 Add to the queue on the remote service and return immediately (does not wait for pinned status). Default: `false`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Cid": "<string>",
     *   "Name": "<string>",
     *   "Status": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/pin/remote/add")
    suspend fun pinRemoteAdd(
        @Query("arg") arg0: String,
        @Query("service") service1: String,
        @Query("name") name2: String,
        @Query("background") background3: Boolean
    ): JSONObject

    /**
     * List objects pinned to remote pinning service.
     * @param service0 Name of the remote pinning service to use (mandatory). Required: no.
     * @param name1 Return pins with names that contain the value provided (case-sensitive, exact match). Required: no.
     * @param cid2 Return pins for the specified CIDs (comma-separated). Required: no.
     * @param status3 Return pins with the specified statuses (queued,pinning,pinned,failed). Default: `[pinned]`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Cid": "<string>",
     *   "Name": "<string>",
     *   "Status": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/pin/remote/ls")
    suspend fun pinRemoteLs(
        @Query("service") service0: String,
        @Query("name") name1: String,
        @Query("cid") cid2: Array<*>,
        @Query("status") status3: Array<*>
    ): JSONObject

    /**
     * Remove pins from remote pinning service.
     * @param service0 Name of the remote pinning service to use (mandatory). Required: no.
     * @param name1 Remove pins with names that contain provided value (case-sensitive, exact match). Required: no.
     * @param cid2 Remove pins for the specified CIDs. Required: no.
     * @param status3 Remove pins with the specified statuses (queued,pinning,pinned,failed). Default: `[pinned]`. Required: no.
     * @param force4 Allow removal of multiple pins matching the query without additional confirmation. Default: `false`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/pin/remote/rm")
    suspend fun pinRemoteRm(
        @Query("service") service0: String,
        @Query("name") name1: String,
        @Query("cid") cid2: Array<*>,
        @Query("status") status3: Array<*>,
        @Query("force") force4: Boolean
    ): ResponseBody

    /**
     * Add remote pinning service.
     * @param arg0 Service name. Required: **yes**.
     * @param arg1 Service endpoint. Required: **yes**.
     * @param arg2 Service key. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/pin/remote/service/add")
    suspend fun pinRemoteServiceAdd(
        @Query("arg") arg0: String,
        @Query("arg") arg1: String,
        @Query("arg") arg2: String
    ): ResponseBody

    /**
     * List remote pinning services.
     * @param stat0 Try to fetch and display current pin count on remote service (queued/pinning/pinned/failed). Default: `false`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "RemoteServices": [
     *     {
     *       "ApiEndpoint": "<string>",
     *       "Service": "<string>",
     *       "Stat": {
     *         "PinCount": {
     *           "Failed": "<int>",
     *           "Pinned": "<int>",
     *           "Pinning": "<int>",
     *           "Queued": "<int>"
     *         },
     *         "Status": "<string>"
     *       }
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/pin/remote/service/ls")
    suspend fun pinRemoteServiceLs(@Query("stat") stat0: Boolean): JSONObject

    /**
     * Remove remote pinning service.
     * @param arg0 Name of remote pinning service to remove. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/pin/remote/service/rm")
    suspend fun pinRemoteServiceRm(@Query("arg") arg0: String): ResponseBody

    /**
     * Remove pinned objects from local storage.
     * @param arg0 Path to object(s) to be unpinned. Required: **yes**.
     * @param recursive1 Recursively unpin the object linked to by the specified object(s). Default: `true`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Pins": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/pin/rm")
    suspend fun pinRm(@Query("arg") arg0: String, @Query("recursive") recursive1: Boolean): JSONObject

    /**
     * Update a recursive pin.
     * @param arg0 Path to old object. Required: **yes**.
     * @param arg1 Path to a new object to be pinned. Required: **yes**.
     * @param unpin2 Remove the old pin. Default: `true`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Pins": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/pin/update")
    suspend fun pinUpdate(
        @Query("arg") arg0: String,
        @Query("arg") arg1: String,
        @Query("unpin") unpin2: Boolean
    ): JSONObject

    /**
     * Verify that recursive pins are complete.
     * @param verbose0 Also write the hashes of non-broken pins. Required: no.
     * @param quiet1 Write just hashes of broken pins. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Cid": "<string>",
     *   "PinStatus": {
     *     "BadNodes": [
     *       {
     *         "Cid": "<string>",
     *         "Err": "<string>"
     *       }
     *     ],
     *     "Ok": "<bool>"
     *   }
     * }
     *
     * </pre>*/
    @POST("/api/v0/pin/verify")
    suspend fun pinVerify(@Query("verbose") verbose0: Boolean, @Query("quiet") quiet1: Boolean): JSONObject

    /**
     * Send echo request packets to IPFS hosts.
     * @param arg0 ID of peer to be pinged. Required: **yes**.
     * @param count1 Number of ping messages to send. Default: `10`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Success": "<bool>",
     *   "Text": "<string>",
     *   "Time": "<duration-ns>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/ping")
    suspend fun ping(@Query("arg") arg0: String, @Query("count") count1: Int): JSONObject

    /**
     * List subscribed topics by name.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/pubsub/ls")
    suspend fun pubsubLs(): JSONObject

    /**
     * List peers we are currently pubsubbing with.
     * @param arg0 Topic to list connected peers of. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/pubsub/peers")
    suspend fun pubsubPeers(@Query("arg") arg0: String): JSONObject

    /**
     * Subscribe to messages on a given topic.
     * @param arg0 Name of topic to subscribe to. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "data": "<string>",
     *   "from": "<string>",
     *   "seqno": "<string>",
     *   "topicIDs": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/pubsub/sub")
    suspend fun pubsubSub(@Query("arg") arg0: String): JSONObject

    /**
     * List links (references) from an object.
     * @param arg0 Path to the object(s) to list refs from. Required: **yes**.
     * @param format1 Emit edges with given format. Available tokens: &lt;src&gt; &lt;dst&gt; &lt;linkname&gt;. Default: &lt;dst&gt;. Default: `<dst>`. Required: no.
     * @param edges2 Emit edge format: `&lt;from&gt; -&gt; &lt;to&gt;`. Required: no.
     * @param unique3 Omit duplicate refs from output. Required: no.
     * @param recursive4 Recursively list links of child nodes. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Err": "<string>",
     *   "Ref": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/refs")
    suspend fun refs(
        @Query("arg") arg0: String,
        @Query("format") format1: String,
        @Query("edges") edges2: Boolean,
        @Query("unique") unique3: Boolean,
        @Query("recursive") recursive4: Boolean
    ): JSONObject

    /**
     * List all local references.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Err": "<string>",
     *   "Ref": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/refs/local")
    suspend fun refsLocal(): JSONObject

    /**
     * Remove repo lockfiles.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Message": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/repo/fsck")
    suspend fun repoFsck(): JSONObject

    /**
     * Perform a garbage collection sweep on the repo.
     * @param quiet0 Write minimal output. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Error": "<string>",
     *   "Key": {
     *     "/": "<cid-string>"
     *   }
     * }
     *
     * </pre>*/
    @POST("/api/v0/repo/gc")
    suspend fun repoGc(@Query("quiet") quiet0: Boolean): JSONObject

    /**
     * Get stats for the currently used repo.
     * @param human0 Print sizes in human readable format (e.g., 1K 234M 2G). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "NumObjects": "<uint64>",
     *   "RepoPath": "<string>",
     *   "SizeStat": {
     *     "RepoSize": "<uint64>",
     *     "StorageMax": "<uint64>"
     *   },
     *   "Version": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/repo/stat")
    suspend fun repoStat(@Query("human") human0: Boolean): JSONObject

    /**
     * Verify all blocks in repo are not corrupted.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Msg": "<string>",
     *   "Progress": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/repo/verify")
    suspend fun repoVerify(): JSONObject

    /**
     * Show the repo version.
     * @param quiet0 Write minimal output. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Version": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/repo/version")
    suspend fun repoVersion(@Query("quiet") quiet0: Boolean): JSONObject

    /**
     * Resolve the value of names to IPFS.
     * @param arg0 The name to resolve. Required: **yes**.
     * @param recursive1 Resolve until the result is an IPFS name. Default: `true`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Path": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/resolve")
    suspend fun resolve(@Query("arg") arg0: String, @Query("recursive") recursive1: Boolean): JSONObject

    /**
     * Shut down the IPFS daemon.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/shutdown")
    suspend fun shutdown(): ResponseBody

    /**
     * Show some diagnostic information on the bitswap agent.
     * @param verbose0 Print extra information. Required: no.
     * @param human1 Print sizes in human readable format (e.g., 1K 234M 2G). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "BlocksReceived": "<uint64>",
     *   "BlocksSent": "<uint64>",
     *   "DataReceived": "<uint64>",
     *   "DataSent": "<uint64>",
     *   "DupBlksReceived": "<uint64>",
     *   "DupDataReceived": "<uint64>",
     *   "MessagesReceived": "<uint64>",
     *   "Peers": [
     *     "<string>"
     *   ],
     *   "ProvideBufLen": "<int>",
     *   "Wantlist": [
     *     {
     *       "/": "<cid-string>"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/stats/bitswap")
    suspend fun statsBitswap(@Query("verbose") verbose0: Boolean, @Query("human") human1: Boolean): JSONObject

    /**
     * Print IPFS bandwidth information.
     * @param peer0 Specify a peer to print bandwidth for. Required: no.
     * @param proto1 Specify a protocol to print bandwidth for. Required: no.
     * @param poll2 Print bandwidth at an interval. Required: no.
     * @param interval3 Time interval to wait between updating output, if &#39;poll&#39; is true.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "RateIn": "<float64>",
     *   "RateOut": "<float64>",
     *   "TotalIn": "<int64>",
     *   "TotalOut": "<int64>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/stats/bw")
    suspend fun statsBw(
        @Query("peer") peer0: String,
        @Query("proto") proto1: String,
        @Query("poll") poll2: Boolean,
        @Query("interval") interval3: String
    ): JSONObject

    /**
     * Returns statistics about the node&#39;s DHT(s).
     * @param arg0 The DHT whose table should be listed (wanserver, lanserver, wan, lan). wan and lan refer to client routing tables. When using the experimental DHT client only WAN is supported. Defaults to wan and lan. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Buckets": [
     *     {
     *       "LastRefresh": "<string>",
     *       "Peers": [
     *         {
     *           "AgentVersion": "<string>",
     *           "Connected": "<bool>",
     *           "ID": "<string>",
     *           "LastQueriedAt": "<string>",
     *           "LastUsefulAt": "<string>"
     *         }
     *       ]
     *     }
     *   ],
     *   "Name": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/stats/dht")
    suspend fun statsDht(@Query("arg") arg0: String): JSONObject

    /**
     * Returns statistics about the node&#39;s (re)provider system.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "AvgProvideDuration": "<duration-ns>",
     *   "LastReprovideBatchSize": "<int>",
     *   "LastReprovideDuration": "<duration-ns>",
     *   "TotalProvides": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/stats/provide")
    suspend fun statsProvide(): JSONObject

    /**
     * Get stats for the currently used repo.
     * @param human0 Print sizes in human readable format (e.g., 1K 234M 2G). Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "NumObjects": "<uint64>",
     *   "RepoPath": "<string>",
     *   "SizeStat": {
     *     "RepoSize": "<uint64>",
     *     "StorageMax": "<uint64>"
     *   },
     *   "Version": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/stats/repo")
    suspend fun statsRepo(@Query("human") human0: Boolean): JSONObject

    /**
     * List known addresses. Useful for debugging.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Addrs": {
     *     "<string>": [
     *       "<string>"
     *     ]
     *   }
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/addrs")
    suspend fun swarmAddrs(): JSONObject

    /**
     * List interface listening addresses.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/addrs/listen")
    suspend fun swarmAddrsListen(): JSONObject

    /**
     * List local addresses.
     * @param id0 Show peer ID in addresses. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/addrs/local")
    suspend fun swarmAddrsLocal(@Query("id") id0: Boolean): JSONObject

    /**
     * Open connection to a given address.
     * @param arg0 Address of peer to connect to. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/connect")
    suspend fun swarmConnect(@Query("arg") arg0: String): JSONObject

    /**
     * Close connection to a given address.
     * @param arg0 Address of peer to disconnect from. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/disconnect")
    suspend fun swarmDisconnect(@Query("arg") arg0: String): JSONObject

    /**
     * Manipulate address filters.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/filters")
    suspend fun swarmFilters(): JSONObject

    /**
     * Add an address filter.
     * @param arg0 Multiaddr to filter. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/filters/add")
    suspend fun swarmFiltersAdd(@Query("arg") arg0: String): JSONObject

    /**
     * Remove an address filter.
     * @param arg0 Multiaddr filter to remove. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Strings": [
     *     "<string>"
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/filters/rm")
    suspend fun swarmFiltersRm(@Query("arg") arg0: String): JSONObject

    /**
     * Add peers into the peering subsystem.
     * @param arg0 address of peer to add into the peering subsystem Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "ID": "<peer-id>",
     *   "Status": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/peering/add")
    suspend fun swarmPeeringAdd(@Query("arg") arg0: String): JSONObject

    /**
     * List peers registered in the peering subsystem.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Peers": [
     *     {
     *       "Addrs": [
     *         "<multiaddr-string>"
     *       ],
     *       "ID": "peer-id"
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/peering/ls")
    suspend fun swarmPeeringLs(): JSONObject

    /**
     * Remove a peer from the peering subsystem.
     * @param arg0 ID of peer to remove from the peering subsystem Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "ID": "<peer-id>",
     *   "Status": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/peering/rm")
    suspend fun swarmPeeringRm(@Query("arg") arg0: String): JSONObject

    /**
     * List peers with open connections.
     * @param verbose0 display all extra information. Required: no.
     * @param streams1 Also list information about open streams for each peer. Required: no.
     * @param latency2 Also list information about latency to each peer. Required: no.
     * @param direction3 Also list information about the direction of connection. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Peers": [
     *     {
     *       "Addr": "<string>",
     *       "Direction": "<int>",
     *       "Latency": "<string>",
     *       "Muxer": "<string>",
     *       "Peer": "<string>",
     *       "Streams": [
     *         {
     *           "Protocol": "<string>"
     *         }
     *       ]
     *     }
     *   ]
     * }
     *
     * </pre>*/
    @POST("/api/v0/swarm/peers")
    suspend fun swarmPeers(
        @Query("verbose") verbose0: Boolean,
        @Query("streams") streams1: Boolean,
        @Query("latency") latency2: Boolean,
        @Query("direction") direction3: Boolean
    ): JSONObject

    /**
     * Export a tar file from IPFS.
     * @param arg0 ipfs path of archive to export. Required: **yes**.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/tar/cat")
    suspend fun tarCat(@Query("arg") arg0: String): ResponseBody

    /**
     *
     * @param arg0 Arguments for subcommand. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * This endpoint returns a `text/plain` response body.
     * </pre>*/
    @POST("/api/v0/update")
    suspend fun update(@Query("arg") arg0: String): ResponseBody

    /**
     * Add URL via urlstore.
     * @param arg0 URL to add to IPFS Required: **yes**.
     * @param trickle1 Use trickle-dag format for dag generation. Required: no.
     * @param pin2 Pin this object when adding. Default: `true`. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Key": "<string>",
     *   "Size": "<int>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/urlstore/add")
    suspend fun urlstoreAdd(
        @Query("arg") arg0: String,
        @Query("trickle") trickle1: Boolean,
        @Query("pin") pin2: Boolean
    ): JSONObject

    /**
     * Show IPFS version information.
     * @param number0 Only show the version number. Required: no.
     * @param commit1 Show the commit hash. Required: no.
     * @param repo2 Show repo version. Required: no.
     * @param all3 Show all version information. Required: no.
     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Commit": "<string>",
     *   "Golang": "<string>",
     *   "Repo": "<string>",
     *   "System": "<string>",
     *   "Version": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/version")
    suspend fun version(
        @Query("number") number0: Boolean,
        @Query("commit") commit1: Boolean,
        @Query("repo") repo2: Boolean,
        @Query("all") all3: Boolean
    ): JSONObject

    /**
     * Shows information about dependencies used for build.

     * @return On success, the call to this endpoint will return with 200 and the following body:<br><pre>
     *
     * {
     *   "Path": "<string>",
     *   "ReplacedBy": "<string>",
     *   "Sum": "<string>",
     *   "Version": "<string>"
     * }
     *
     * </pre>*/
    @POST("/api/v0/version/deps")
    suspend fun versionDeps(): JSONObject
}