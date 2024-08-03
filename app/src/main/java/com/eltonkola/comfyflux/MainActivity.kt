package com.eltonkola.comfyflux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.eltonkola.comfyflux.app.FluxAPIApp
import com.eltonkola.comfyflux.ui.theme.ComfyFluxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComfyFluxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FluxAPIApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
