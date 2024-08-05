package com.eltonkola.comfyflux.app.model

import kotlinx.serialization.Serializable

@Serializable
data class SystemStats(
    val system: System,
    val devices: List<Device>
){
    @Serializable
    data class System(
        val os: String,
        val python_version: String,
        val embedded_python: Boolean
    )
    @Serializable
    data class Device(
        val name: String,
        val type: String,
        val vram_total: Long,
        val vram_free: Long,
        val torch_vram_total: Long,
        val torch_vram_free: Long,
        val index : Int? = null
    )
}
