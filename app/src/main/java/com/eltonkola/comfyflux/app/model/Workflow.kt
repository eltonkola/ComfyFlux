package com.eltonkola.comfyflux.app.model

import com.eltonkola.comfyflux.R

data class Workflow(
    val name: String,
    val description : String,
   val workflowRes: Int
) {
}

val workflows = listOf(
    Workflow(
        "4 steps Flux",
        "Fastest way to render FLUX.1-schnell, use 512x512 for a faster generation.",
        R.raw.workflow_flux_schnell
    ),
    Workflow(
        "20 steps Flux dev",
        "Better quality with FLUX.1-dev, use 512x512 for a faster generation.",
        R.raw.workflow_flux_dev
    )
)