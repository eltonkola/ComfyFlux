package com.eltonkola.comfyflux.app.model

import com.eltonkola.comfyflux.R

data class WorkflowFile(
    val name: String,
    val description : String,
   val workflowRes: Int
) {
}

val workflows = listOf(
    WorkflowFile(
        "4 steps Flux",
        "Fastest way to render FLUX.1-schnell, use 512x512 for a faster generation.",
        R.raw.workflow_flux_schnell
    ),
    WorkflowFile(
        "20 steps Flux dev",
        "Better quality with FLUX.1-dev, use 512x512 for a faster generation.",
        R.raw.workflow_flux_dev
    ),
    WorkflowFile(
        "4 steps SDXL Lighting",
        "Great images on only 4 steps lora. The best of Stable Diffusion SDXL.",
        R.raw.sdxl_lighting_4steps
    )
)