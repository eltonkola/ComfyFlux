package com.eltonkola.comfyflux.app.netwrok

import android.util.Log
import com.eltonkola.comfyflux.BuildConfig
import com.eltonkola.comfyflux.app.data.AppSettings
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement


@Serializable
data class Message(val role: String, val content: String)

@Serializable
data class RequestBody(
    val messages: List<Message>,
    val model: String,
    val temperature: Double,
    val max_tokens: Int,
    val top_p: Int,
    val stream: Boolean
)

@Serializable
data class Choice(
    val index: Int,
    val message: Message,
    val logprobs: JsonElement? = null,
    @SerialName("finish_reason")
    val finishReason: String
)

@Serializable
data class Usage(
    @SerialName("prompt_tokens")
    val promptTokens: Int,
    @SerialName("completion_tokens")
    val completionTokens: Int,
    @SerialName("total_tokens")
    val totalTokens: Int,
    @SerialName("prompt_time")
    val promptTime: Double? = null,
    @SerialName("completion_time")
    val completionTime: Double? = null,
    @SerialName("total_time")
    val totalTime: Double? = null
)

@Serializable
data class XGroq(val id: String)

@Serializable
data class ApiResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage,
    @SerialName("system_fingerprint")
    val systemFingerprint: String? = null,
    @SerialName("x_groq")
    val xGroq: XGroq? = null
)

class GroqAPI(private val appSettings: AppSettings) {

    companion object {
        private const val SHARED_API_KEY = BuildConfig.GROQ_API_KEY
    }

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(InternalAPI::class)
    suspend fun enrichPrompt(initialPrompt: String): String {

        val apiKey = appSettings.settingsState.value.grqApiKey.ifEmpty { SHARED_API_KEY }

        Log.d("GrowAPI", "enrichPrompt - $apiKey -  initialPrompt: $initialPrompt")
        val url = "https://api.groq.com/openai/v1/chat/completions"

        val requestBody = RequestBody(
            messages = listOf(
                Message(
                    "system",
                    "You are a creative assistant specialized in enhancing image generation prompts. Your task is to take a short prompt and expand it into a more detailed and vivid description, suitable for AI image generation."
                ),
                Message(
                    "user",
                    "Please enrich this image generation prompt: '$initialPrompt', return the prompt only, without anything else"
                )
            ),
            model = "mixtral-8x7b-32768",
            temperature = 0.7,
            max_tokens = 300,
            top_p = 1,
            stream = false
        )
        val jsonBody = json.encodeToString(requestBody)

        val response: HttpResponse = client.post(url) {
            header(HttpHeaders.Authorization, "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            body = jsonBody
        }

        val responseBody = response.bodyAsText()
        Log.d("GrowAPI", "Received response: $responseBody")

        val apiResponse = Json.decodeFromString<ApiResponse>(responseBody)
        Log.d("GrowAPI", "Parsed apiResponse: $apiResponse")

        return apiResponse.choices.firstOrNull()?.message?.content ?: "Failed to enrich prompt"

    }


}
