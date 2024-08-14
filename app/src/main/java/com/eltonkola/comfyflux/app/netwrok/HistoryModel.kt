package com.eltonkola.comfyflux.app.netwrok

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class HistoryResponse(
    val prompt: List<JsonElement>,
    val outputs: Map<String, Output>,
    val status: Status
) {

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

    @Serializable
    data class Status(
        val status_str: String,
        val completed: Boolean,
        val messages: List<List<JsonElement>>
    )
}


