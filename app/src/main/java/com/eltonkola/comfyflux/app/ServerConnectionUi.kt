package com.eltonkola.comfyflux.app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.eltonkola.comfyflux.app.netwrok.DEFAULT_URL
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Refresh
import com.eltonkola.comfyflux.ui.theme.ikona.Server

@Composable
fun ServerConnectionUi(viewModel: MainViewModel, uiState: ImageGenerationUiState) {
    val keyboardController = LocalSoftwareKeyboardController.current


    TextField(
        value = uiState.server,
        onValueChange = { newText -> viewModel.updateSeverUrl(newText) },
        label = { Text("ComfyUi Server IP and Port") },
        placeholder = { Text("e.g., $DEFAULT_URL") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        leadingIcon = {
            Icon(
                imageVector = Ikona.Server,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        },
        supportingText = {
            val supportingText = if (uiState.loadingStats) {
                "Connecting..."
            } else if (uiState.stats == null) {
                "Cant reach the server!"
            } else {
                "Connected to: ${uiState.stats.system.os} \nPython: ${uiState.stats.system.python_version}"
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = supportingText
                )
                val device = uiState.stats?.devices?.firstOrNull()
                if (device != null) {
                    LinearProgressIndicator(
                        progress = { (device.vram_total - device.vram_free).toFloat() / device.vram_total },
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .align(Alignment.TopEnd),
                        color = Color.DarkGray,
                        trackColor = Color.Gray,
                    )

                }
            }
        },
        trailingIcon = {
            if (uiState.loadingStats) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp)
                )
            } else {
                val bgColor = if (uiState.stats == null) Color.Red else Color.Green
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                        .background(color = bgColor, shape = CircleShape)
                ) {
                    IconButton(onClick = { viewModel.checkStatus() }) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Ikona.Refresh,
                            contentDescription = "Connect",
                            tint =  MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        },
        keyboardActions = KeyboardActions(
            onDone = {
                viewModel.checkStatus()
                keyboardController?.hide()
            }
        )
    )

}
