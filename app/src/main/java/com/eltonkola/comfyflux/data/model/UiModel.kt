package com.eltonkola.comfyflux.data.model

import com.eltonkola.comfyflux.data.netwrok.DEFAULT_URL
import com.eltonkola.comfyflux.app.usecase.generateRandom16DigitNumber


data class HistoryUiState(
    val history: List<HistoryItem> = emptyList(),
    val loading: Boolean = true,
    val error: Boolean = false,
    val silentLoading: Boolean = false
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
) {
    data class Workflow(
        val id: String,
        val prompt: String
    )
}

data class ProgressGenerationUIState(
    val promptId: String = "",
    val progress: Int = 0,
    val maxProgress: Int = 0,
    val queueRemaining: Int = 0,
    val generatedImages: List<String> = emptyList(),
    val statusMessage: String = "",
    val error: String = "",
    val executing: Boolean = false,
    val allNodes: List<String> = emptyList(),
    val remainingNodes: List<String> = emptyList()
) {
    fun totalProgress(): Float {
        return (allNodes.size - remainingNodes.size) / allNodes.size.toFloat()
    }

    fun partialProgress(): Float {
        return progress / maxProgress.toFloat()
    }
}

data class ImageGenerationUiState(
    val server: String = DEFAULT_URL,

    val loadingStats: Boolean = false,
    val stats: SystemStats? = null,

    val workflow: WorkflowFile = workflows.first(),

    val prompt: String = "",
    val width: Int = 512,
    val height: Int = 512,
    val batchSize: Int = 1,
    val seed: Long = generateRandom16DigitNumber(),
    val isRandom: Boolean = true
)

data class ImageViewerUiState(
    val images: List<String> = emptyList(),
    val selected: Int = 0
)

