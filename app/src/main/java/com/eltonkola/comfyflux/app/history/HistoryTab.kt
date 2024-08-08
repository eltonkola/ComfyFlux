package com.eltonkola.comfyflux.app.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun HistoryTab(viewModel: HistoryQueueViewModel, uiState: HistoryUiState) {

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.loading) {
            CircularProgressIndicator()
        } else if (uiState.error) {
            Column {
                Text(text = "Error loading history!")
                TextButton(onClick = { viewModel.loadHistory() }) {
                    Text(text = "Retry")
                }
            }
        } else {
            if (uiState.history.isEmpty()) {
                Text(text = "No History. Start creating something!")
            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(uiState.history) {
                        HistoryRowUi(it)
                        Spacer(modifier = Modifier.size(1.dp))
                    }

                }
            }
        }
    }

}

@Composable
fun HistoryRowUi(item: HistoryItem) {
    Column {
        Text(text = "Nr: ${item.images.size} success: ${item.success} - completed: ${item.completed}")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(item.images) {
                AsyncImage(
                    modifier = Modifier
                        .size(120.dp)
                        .background(MaterialTheme.colorScheme.secondary),
                    model = item.images.first(),
                    contentDescription = null,
                )
            }
        }
    }
}