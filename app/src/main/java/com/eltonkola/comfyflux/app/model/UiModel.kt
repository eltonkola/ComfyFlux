package com.eltonkola.comfyflux.app.model


data class HistoryUiState(
    val history: List<HistoryItem> = emptyList(),
    val loading: Boolean = true,
    val error: Boolean = false
)

data class QueueUiState(
    val queue: Queue? = null,
    val loading: Boolean = true,
    val error: Boolean = false
)

data class HistoryItem(
    val images: List<String>,
    val success: Boolean,
    val completed: Boolean,
    val prompt: Workflow,
    val id: String
)

data class Queue(
    val running: List<Workflow>,
    val pending: List<Workflow>,
){
    data class Workflow(
        val id: String,
        val prompt: String
    )
}

data class ProgressGenerationUIState(
    val promptId: String="",
    val progress: Int = 0,
    val maxProgress: Int = 1,
    val queueRemaining: Int = 0,
    val generatedImages: List<String> = emptyList(),
    val statusMessage: String = "",
    val error: String = "",
    val executing: Boolean = false
)

