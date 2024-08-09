package com.eltonkola.comfyflux.app.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
sealed class WSMessage {
    abstract val promptId: String?

    @Serializable
    data class PromptMessage(
        override val promptId: String,
        val number: Int,
        val nodeErrors: Map<String, String> = emptyMap()
    ) : WSMessage()

    @Serializable
    data class StatusMessage(
        val status: Status,
        val sid: String? = null,
        override val promptId: String? = null
    ) : WSMessage()

    @Serializable
    data class ExecutionCachedMessage(
        override val promptId: String,
        val nodes: List<String>,
        val timestamp: Long
    ) : WSMessage()

    @Serializable
    data class ExecutingMessage(
        override val promptId: String,
        val node: String
    ) : WSMessage()

    @Serializable
    data class ProgressMessage(
        override val promptId: String,
        val value: Int,
        val max: Int,
        val node: String
    ) : WSMessage()

    @Serializable
    data class ExecutedMessage(
        override val promptId: String,
        val node: String,
        val output: Output
    ) : WSMessage()

    @Serializable
    data class ExecutionSuccessMessage(
        override val promptId: String,
        val timestamp: Long
    ) : WSMessage()

    @Serializable
    data class Status(
        val execInfo: ExecInfo? = null
    )

    @Serializable
    data class ExecInfo(
        val queueRemaining: Int
    )

    @Serializable
    data class Output(
        val images: List<Image>
    )

    @Serializable
    data class Image(
        val filename: String,
        val subfolder: String,
        val type: String
    )

    companion object {
        private val json = Json { ignoreUnknownKeys = true }

        fun fromJson(jsonString: String): WSMessage {
            val baseMessage = json.decodeFromString<BaseMessage>(jsonString)
            return when (baseMessage.type) {
                null -> json.decodeFromString<PromptMessage>(jsonString)
                "status" -> json.decodeFromString<StatusMessage>(jsonString)
                "execution_cached" -> json.decodeFromString<ExecutionCachedMessage>(jsonString)
                "executing" -> json.decodeFromString<ExecutingMessage>(jsonString)
                "progress" -> json.decodeFromString<ProgressMessage>(jsonString)
                "executed" -> json.decodeFromString<ExecutedMessage>(jsonString)
                "execution_success" -> json.decodeFromString<ExecutionSuccessMessage>(jsonString)
                else -> throw IllegalArgumentException("Unknown message type: ${baseMessage.type}")
            }
        }
    }

    @Serializable
    private data class BaseMessage(val type: String?)
}
