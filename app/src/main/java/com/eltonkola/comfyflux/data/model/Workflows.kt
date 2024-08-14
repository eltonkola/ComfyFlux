package com.eltonkola.comfyflux.data.model

data class WorkflowFile(
    val name: String,
    val description: String,
    val workflowUri: String,
    val isAsset: Boolean
)

val workflows = listOf(
    WorkflowFile(
        "4 steps Flux",
        "Fastest way to render FLUX.1-schnell, use 512x512 for a faster generation.",
        "file:///android_asset/workflow_flux_schnell.json",
        true
    ),
    WorkflowFile(
        "20 steps Flux dev",
        "Better quality with FLUX.1-dev, use 512x512 for a faster generation.",
        "file:///android_asset/workflow_flux_dev.json",
        true
    ),
    WorkflowFile(
        "4 steps SDXL Lighting",
        "Great images on only 4 steps lora. The best of Stable Diffusion SDXL.",
        "file:///android_asset/sdxl_lighting_4steps.json",
        true
    )
)