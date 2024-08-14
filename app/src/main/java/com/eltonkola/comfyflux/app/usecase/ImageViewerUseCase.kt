package com.eltonkola.comfyflux.app.usecase

import com.eltonkola.comfyflux.app.model.ImageViewerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ImageViewerUseCase {

    private val _imageUiState = MutableStateFlow(ImageViewerUiState())
    val imageUiState: StateFlow<ImageViewerUiState> = _imageUiState.asStateFlow()

    fun viewImage(images: List<String> = emptyList(), selected: Int = 0) {
        _imageUiState.value = ImageViewerUiState(images, selected)
    }

}