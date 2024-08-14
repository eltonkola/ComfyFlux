package com.eltonkola.comfyflux.app.usecase

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import com.eltonkola.comfyflux.data.model.ImageGenerationUiState
import com.eltonkola.comfyflux.data.model.PromptRequest
import com.eltonkola.comfyflux.data.model.Workflow
import com.eltonkola.comfyflux.data.model.WorkflowFile
import com.eltonkola.comfyflux.data.model.updatePrompt
import com.eltonkola.comfyflux.data.model.updateSeed
import com.eltonkola.comfyflux.data.model.updateSizeAndBatch
import com.eltonkola.comfyflux.data.netwrok.FluxAPI
import com.eltonkola.comfyflux.data.netwrok.GroqAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.random.Random

class MainUseCase(
    private val fluxAPI: FluxAPI,
    private val groqAPI: GroqAPI,
    private val applicationContext: Application
) {

    private val _uiState = MutableStateFlow(ImageGenerationUiState())
    val uiState: StateFlow<ImageGenerationUiState> = _uiState.asStateFlow()

    suspend fun updateSeverUrl(url: String) {
        fluxAPI.setServerAddress(url)
        _uiState.update { it.copy(server = url) }
    }

    fun updatePrompt(prompt: String) {
        _uiState.update { it.copy(prompt = prompt) }
    }

    fun updateWorkflow(workflow: WorkflowFile) {
        _uiState.update { it.copy(workflow = workflow) }
    }

    fun setImageWidth(width: Int) {
        _uiState.update { it.copy(width = width) }
    }

    fun setImageHeight(height: Int) {
        _uiState.update { it.copy(height = height) }
    }

    fun setBatchSize(batchSize: Int) {
        _uiState.update { it.copy(batchSize = batchSize) }
    }

    fun setSeed(seed: Long, isRandom: Boolean) {
        _uiState.update { it.copy(seed = seed, isRandom = isRandom) }
    }

    suspend fun enrichPrompt() {
        try {
            val richPrompt = groqAPI.enrichPrompt(uiState.value.prompt)
            _uiState.update { it.copy(prompt = richPrompt) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun randomSeed() {
        _uiState.update { it.copy(seed = generateRandom16DigitNumber()) }
    }

    suspend fun checkStatus() {
        _uiState.update { it.copy(loadingStats = true) }
        val status = fluxAPI.checkSystemStats()
        _uiState.update { it.copy(loadingStats = false, stats = status) }
    }


    private fun loadWorkflow(): PromptRequest {
        val seed = if (uiState.value.isRandom) {
            val newSeed = generateRandom16DigitNumber()
            _uiState.update { it.copy(seed = newSeed) }
            newSeed
        } else {
            uiState.value.seed
        }

        val workflowConfig = _uiState.value.workflow
        val workflowInput = applicationContext.getWorkflowAsText(workflowConfig) ?: throw Exception(
            "Error reading workflow!"
        )
        val workflow = Json.decodeFromString<Workflow>(workflowInput)
        workflow.updateSizeAndBatch(
            width = uiState.value.width,
            height = uiState.value.height,
            batchSize = uiState.value.batchSize
        )
        workflow.updatePrompt(prompt = uiState.value.prompt.cleanPrompt())
        workflow.updateSeed(seed = seed)
        return PromptRequest(fluxAPI.clientId, workflow)
    }

    suspend fun generateImages(start: () -> Unit, stop: () -> Unit) {
        if (uiState.value.prompt.isBlank()) {
            return
        }
        start()
        try {
            val prompt = loadWorkflow()
            Log.d("FluxApp", "prompt: $prompt")
            withContext(Dispatchers.IO) {
                fluxAPI.generateImage(prompt)
            }
            checkStatus()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        stop()
    }


    private fun String.cleanPrompt(): String {
        return this
            .replace("\\", "\\\\") // Escape backslashes
            .replace("\"", "\\\"") // Escape double quotes
            .replace("\n", "") // Remove newlines
            .replace("\r", "") // Remove carriage returns
            .replace("\t", "") // Remove tabs
    }
}


fun Context.getWorkflowAsText(workflow: WorkflowFile): String? {
    return try {
        if (workflow.isAsset) {
            // Load from assets
            val fileName = workflow.workflowUri.removePrefix("file:///android_asset/")
            assets.open(fileName).bufferedReader().use { it.readText() }
        } else {
            Log.d("ComfyFlux", "reading file: ${workflow.workflowUri}")
            val uri = Uri.parse(workflow.workflowUri)
            contentResolver.openInputStream(uri)?.bufferedReader().use {
                it?.readText()
            }

        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun generateRandom16DigitNumber(): Long {
    val random = Random.nextLong(100000000000000, 999999999999999)
    return random
}