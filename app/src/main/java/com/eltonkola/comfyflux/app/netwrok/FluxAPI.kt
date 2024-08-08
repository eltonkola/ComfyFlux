package com.eltonkola.comfyflux.app.netwrok

import android.util.Log
import com.eltonkola.comfyflux.app.cleanPrompt
import com.eltonkola.comfyflux.app.history.HistoryItem
import com.eltonkola.comfyflux.app.model.SystemStats
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readBytes
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import java.util.Date
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


@Serializable
data class WebSocketMessage(
    val type: String,
    val data: WebSocketData? = null
)

@Serializable
data class WebSocketData(
    val status: ExecInfo? = null,
    val sid: String? = null
)

@Serializable
data class ExecInfo(
    val exec_info: QueueStatus? = null
)

@Serializable
data class QueueStatus(
    val queue_remaining: Int
)


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

    suspend fun queuePrompt(prompt: String): String {
        // val jsonString = Json.encodeToString(prompt)
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

    suspend fun getImage(filename: String, subfolder: String, folderType: String): ByteArray {
        return client.get("http://$serverAddress/view") {
            parameter("filename", filename)
            parameter("subfolder", subfolder)
            parameter("type", folderType)
        }.readBytes()
    }

    suspend fun getHistory(promptId: String): PromptHistory {
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

    suspend fun getImages(prompt: String): Map<String, List<ByteArray>> {
        val promptId = queuePrompt(prompt)
        val outputImages = mutableMapOf<String, MutableList<ByteArray>>()

        // Create a MutableStateFlow to keep track of when the queue is empty
        val queueStatusFlow = MutableStateFlow<Boolean>(false)

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
                    val json = Json { ignoreUnknownKeys = true }
                    val message = json.decodeFromString<WebSocketMessage>(messageText)
                    Log.d("FluxAPI", "Received websocket message: $message")

                    if (message.type == "status") {
                        val status = message.data?.status
                        if (status?.exec_info?.queue_remaining == 0) {
                            // Set the queueStatusFlow to true when queue is empty
                            queueStatusFlow.value = true
                            break
                        }
                    }
                }
            }
        }

        // Wait until the queue is empty
        queueStatusFlow.first { it }

        val historyResponse: PromptHistory = getHistory(promptId)
        val history = historyResponse.outputs
        for ((nodeId, nodeOutput) in history) {
            val imagesOutput = nodeOutput.outputs.map { it.value }.flatMap { elem ->
                elem.images.map { image ->
                    getImage(image.filename, image.subfolder, image.type)
                }
            }.toMutableList()
            outputImages[nodeId] = imagesOutput
        }

        return outputImages
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


    private fun HistoryResponse.normalize(): HistoryItem{

        val prompt = this.prompt[2].jsonObject["6"]!!.jsonObject["inputs"]!!.jsonObject["text"]

        return HistoryItem(
            images = this.outputs.values.flatMap { it.images.filter { it.type == "output" }.map { "http://$serverAddress/view?filename=${it.filename}&type=${it.type}&subfolder=${it.subfolder}"} },
            success = this.status.status_str == "success",
            completed = this.status.completed,
            prompt = prompt.toString().cleanHistoryPrompt()
        )
    }
}

fun String.cleanHistoryPrompt() : String {
    return this.trim('"').trim('\\').removePrefix("\"")
}
