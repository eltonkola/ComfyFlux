package com.eltonkola.comfyflux.app.netwrok

import android.util.Log
import com.eltonkola.comfyflux.app.model.HistoryItem
import com.eltonkola.comfyflux.app.model.ProgressGenerationUIState
import com.eltonkola.comfyflux.app.model.PromptRequest
import com.eltonkola.comfyflux.app.model.Queue
import com.eltonkola.comfyflux.app.model.SystemStats
import com.eltonkola.comfyflux.app.model.WSMessage
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.putJsonArray
import java.util.UUID

@Serializable
data class PromptHistory(val outputs: Map<String, PromptData>)

@Serializable
data class PromptData(
    val prompt: List<JsonElement>,  // Use JsonElement for dynamic content
    val outputs: Map<String, OutputData>,
    val status: Status
)

@Serializable
data class OutputData(
    val images: List<Image>
)

@Serializable
data class Image(
    val filename: String,
    val subfolder: String,
    val type: String
)

@Serializable
data class Status(
    val status_str: String,
    val completed: Boolean,
    val messages: List<List<JsonElement>>
)



@Serializable
data class QueuePromptResponse(val prompt_id: String, val number: Int, val node_errors: Map<String, String>)

const val DEFAULT_URL = "192.168.0.2:8188"

/*
Api docs:
Rest:
https://github.com/comfyanonymous/ComfyUI/issues/2110

WS:
https://github.com/comfyanonymous/ComfyUI/issues/2258
*/

class FluxAPI {

    val clientId = UUID.randomUUID().toString()

    private val client = HttpClient(CIO) {
        install(WebSockets)
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private var serverAddress = DEFAULT_URL

    suspend fun setServerAddress(address: String) {
        serverAddress = address
    }

    private suspend fun queuePrompt(promptRequest: PromptRequest): String {
        val prompt = Json.encodeToString(promptRequest)

        Log.d("FluxAPI", "Sending prompt: $prompt")
        val response = client.post("http://$serverAddress/prompt") {
            contentType(ContentType.Application.Json)
            setBody(prompt)
        }
        val responseBody = response.bodyAsText()
        Log.d("FluxAPI", "Received response: $responseBody")
        val jsonResponse = Json.decodeFromString<QueuePromptResponse>(responseBody)
        return jsonResponse.prompt_id
    }

    private suspend fun getHistory(promptId: String): PromptHistory {
        val response = client.get("http://$serverAddress/history/$promptId")
        val jsonResponse = response.bodyAsText()
        Log.d("FluxAPI", "Received history response: $jsonResponse")
        val json = Json { ignoreUnknownKeys = true } // Configure JSON to ignore unknown keys

        // Parse the JSON response as a JsonObject
        val historyJson = json.decodeFromString<JsonObject>(jsonResponse)
        val historyData = mutableMapOf<String, PromptData>()
        Log.d("FluxAPI", "historyJson $historyJson")
        // Iterate through the dynamic keys
        for ((key, value) in historyJson) {
            Log.d("FluxAPI", "Received key: $key value: $value")


            val outputData = json.decodeFromJsonElement<PromptData>(value)
            historyData[key] = outputData
        }

        return PromptHistory(historyData)
    }

    private val _progressUiState = MutableStateFlow(ProgressGenerationUIState())
    val progressUiState: StateFlow<ProgressGenerationUIState> = _progressUiState.asStateFlow()

    private fun handleProgressMessage(message: WSMessage.ProgressMessage): ProgressGenerationUIState {
        return _progressUiState.value.copy(
            progress = message.value,
            maxProgress = message.max,
            currentNode = message.node
        )
    }

    private fun handleStatusMessage(message: WSMessage.StatusMessage): ProgressGenerationUIState {
        return _progressUiState.value.copy(
            queueRemaining = message.status.execInfo?.queueRemaining ?: 0,
            statusMessage = "Status updated"
        )
    }

    private fun handleExecutedMessage(message: WSMessage.ExecutedMessage): ProgressGenerationUIState {

        val images = message.output.images.map {
            getImage(
                filename = it.filename,
                subfolder = it.subfolder,
                type = it.type
            )
        }

        return _progressUiState.value.copy(
            generatedImages = images,
            statusMessage = "Image generated by ${message.node}"
        )
    }

    private fun handlePromptMessage(message: WSMessage.PromptMessage): ProgressGenerationUIState {
        return _progressUiState.value.copy(
            promptId = message.promptId,
            errors = message.nodeErrors
        )
    }

    private fun handleExecutionSuccessMessage(message: WSMessage.ExecutionSuccessMessage): ProgressGenerationUIState {
        return _progressUiState.value.copy(
            statusMessage = "Execution completed at ${message.timestamp}"
        )
    }

    private fun handleExecutionCachedMessage(message: WSMessage.ExecutionCachedMessage): ProgressGenerationUIState {
        return _progressUiState.value.copy(
            statusMessage = "Execution cached at ${message.timestamp}",
           // cachedNodes = message.nodes
        )
    }

    private fun handleExecutingMessage(message: WSMessage.ExecutingMessage): ProgressGenerationUIState {
        Log.d("FluxAPI", "Nodes before: ${_progressUiState.value.allNodes}")

        val nodes = _progressUiState.value.allNodes.toMutableList().apply {
            var found = false
            val toRemove = mutableListOf<String>()
            this.forEach { s ->
                if(!found && s != message.node){
                    toRemove.add(s)
                } else if (s == message.node){
                    found = true
                }
            }
            removeAll(toRemove)
        }.toList()
        Log.d("FluxAPI", "Nodes after: ${nodes}")


        return _progressUiState.value.copy(
            allNodes = nodes,
            currentNode = message.node,
            statusMessage = "Executing on node ${message.node}"
        )
    }

    suspend fun generateImage(prompt: PromptRequest): List<String> {
        val promptId = queuePrompt(prompt)

        // Create a MutableStateFlow to keep track of when the queue is empty
        val queueStatusFlow = MutableStateFlow(false)
        val allNodes = prompt.prompt.keys.toList()
        _progressUiState.value =  ProgressGenerationUIState(allNodes = allNodes)
        // Start WebSocket connection
        client.webSocket(
            method = HttpMethod.Get,
            host = serverAddress.split(":")[0],
            port = serverAddress.split(":")[1].toInt(),
            path = "/ws?clientId=$clientId"
        ) {
            while (true) {
                val frame = incoming.receive()
                if (frame is Frame.Text) {
                    val messageText = frame.readText()
                    Log.d("FluxAPI", "Received websocket raw: $messageText")

                    val wsMessage = WSMessage.fromJson(messageText)
                    Log.d("FluxAPI", "Received ws message: $wsMessage")

                    val uiState = when (wsMessage) {
                        is WSMessage.PromptMessage -> handlePromptMessage(wsMessage)
                        is WSMessage.StatusMessage -> handleStatusMessage(wsMessage)
                        is WSMessage.ExecutionCachedMessage -> handleExecutionCachedMessage(wsMessage)
                        is WSMessage.ExecutingMessage -> handleExecutingMessage(wsMessage)
                        is WSMessage.ProgressMessage -> handleProgressMessage(wsMessage)
                        is WSMessage.ExecutedMessage -> handleExecutedMessage(wsMessage)
                        is WSMessage.ExecutionSuccessMessage -> handleExecutionSuccessMessage(wsMessage)
                    }

                    _progressUiState.value = uiState

                    if(uiState.queueRemaining == 0){
                        queueStatusFlow.value = true
                        Log.d("FluxAPI", "Received ws message: $wsMessage")

                        break
                    }

                }
            }
        }

        // Wait until the queue is empty
        queueStatusFlow.first { it }

        val historyResponse: PromptHistory = getHistory(promptId)
        val history = historyResponse.outputs
        val images = history.values.flatMap {
            it.outputs.map { it.value }.flatMap { elem ->
                elem.images.map { image ->
                    getImage(image.filename, image.subfolder, image.type)
                }.toMutableList()
            }
        }
        Log.d("FluxAPI", "Images: ${images.size}")
        return images
    }



    suspend fun checkSystemStats(): SystemStats? {
        Log.d("FluxAPI", "call checkSystemStats")
        return try {
            val response = client.get("http://$serverAddress/system_stats") {
                contentType(ContentType.Application.Json)
            }
            val responseBody = response.bodyAsText()
            Log.d("FluxAPI", "Received response: $responseBody")
            val stats = Json.decodeFromString<SystemStats>(responseBody)
            Log.d("FluxAPI", "call checkSystemStats stats: $stats")
            stats
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun fetchHistory(): List<HistoryItem> {
        return withContext(Dispatchers.IO) {
            val response = client.get("http://$serverAddress/history")
            val responseBody = response.bodyAsText()

            Log.d("FluxAPI", "Received fetchHistory responseBody: $responseBody")
            val history = Json.decodeFromString<Map<String, HistoryResponse>>(responseBody)
            Log.d("FluxAPI", "call fetchHistory history: $history")

            history.values.map { it.normalize() }
        }
    }

    suspend fun fetchQueue(): Queue {
        return withContext(Dispatchers.IO) {
            val response = client.get("http://$serverAddress/queue")
            val responseBody = response.bodyAsText()

            Log.d("FluxAPI", "Received fetchQueue responseBody: $responseBody")
            val queue = Json.decodeFromString<QueueResponse>(responseBody)
            Log.d("FluxAPI", "call fetchQueue queue: $queue")

            queue.normalize()
        }
    }

    private fun QueueResponse.normalize() : Queue {
        Log.d("FluxAPI", "call QueueResponse normalize: $this")

        return Queue(
            running = this.queue_running.map { it.toQueueWorkflow() },
            pending = this.queue_pending.map { it.toQueueWorkflow() }
        )
    }

    private fun JsonElement.toQueueWorkflow() : Queue.Workflow {
        val prompt = this.jsonArray[2].jsonObject["6"]!!.jsonObject["inputs"]!!.jsonObject["text"].toString().cleanHistoryPrompt()
        val id = this.jsonArray[1].toString().replace("\"", "")

        Log.d("FluxAPI", "call toQueueWorkflow: $this")


        return Queue.Workflow(
            prompt = prompt,
            id = id
        )
    }

    @OptIn(InternalAPI::class)
    suspend fun deleteHistory(id: String){
        val deleteBody = buildJsonObject {
            putJsonArray("delete") {
                add(id)
            }
        }.toString()

        return withContext(Dispatchers.IO) {
            val response = client.post("http://$serverAddress/history") {
                contentType(ContentType.Application.Json)
                body = deleteBody
            }
            val responseBody = response.bodyAsText()

            Log.d("FluxAPI", "Received deleteHistory: $deleteBody")
            Log.d("FluxAPI", "Received delete history: $responseBody")
        }
    }



    @OptIn(InternalAPI::class)
    suspend fun cancelWorkflow(id: String){
        val deleteBody = buildJsonObject {
            putJsonArray("delete") {
                add(id)
            }
        }.toString()

        return withContext(Dispatchers.IO) {
            val response = client.post("http://$serverAddress/queue") {
                contentType(ContentType.Application.Json)
                body = deleteBody
            }
            val responseBody = response.bodyAsText()

            Log.d("FluxAPI", "Received deleteBody: $deleteBody")

            Log.d("FluxAPI", "Received delete workflow: $responseBody")
        }
    }


    private fun HistoryResponse.normalize(): HistoryItem {

        val prompt = this.prompt[2].jsonObject["6"]!!.jsonObject["inputs"]!!.jsonObject["text"]

        return HistoryItem(
            images = this.outputs.values.flatMap { it.images.filter { it.type == "output" }.map { getImage(it.filename,it.subfolder, it.type) } },
            success = this.status.status_str == "success",
            completed = this.status.completed,
            prompt = prompt.toString().cleanHistoryPrompt()
        )
    }

    suspend fun interrupt() {
        withContext(Dispatchers.IO) {
            val response = client.post("http://$serverAddress/interrupt")
            val responseBody = response.bodyAsText()
            Log.d("FluxAPI", "Received interrupt: $responseBody")
        }
    }

    private fun getImage(filename: String, subfolder: String, type: String) : String {
        return "http://$serverAddress/view?filename=$filename&subfolder=$subfolder&type=$type"
    }

}

fun String.cleanHistoryPrompt() : String {
    return this.trim('"').trim('\\').removePrefix("\"")
}
