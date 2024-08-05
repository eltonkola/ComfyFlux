package com.eltonkola.comfyflux.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun HistoryUi(contentPadding: PaddingValues, onClose: () -> Unit) {
    Column(
        modifier = Modifier.padding(contentPadding)
    ) {
        Text(text = "TODO -  show history of all images on server!")
        Button(onClick = {
            onClose()
        }) {
            Text("Close")
        }

    }
}