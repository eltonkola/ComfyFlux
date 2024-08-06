package com.eltonkola.comfyflux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.eltonkola.comfyflux.app.MainApp
import com.eltonkola.comfyflux.ui.theme.ComfyFluxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComfyFluxTheme {
                MainApp()
            }
        }
    }
}
