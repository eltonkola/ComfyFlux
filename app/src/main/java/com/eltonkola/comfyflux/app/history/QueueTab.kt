package com.eltonkola.comfyflux.app.history

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Cancel
import com.eltonkola.comfyflux.ui.theme.ikona.Copy
import com.eltonkola.comfyflux.ui.theme.ikona.Error
import com.eltonkola.comfyflux.ui.theme.ikona.Refresh
import okhttp3.internal.wait

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueueTab(viewModel: HistoryQueueViewModel, uiState: QueueUiState) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All queue jobs") },
                actions = {
                    IconButton(onClick = { viewModel.loadQueue() }) {
                        Icon(
                            imageVector = Ikona.Refresh,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            if (uiState.loading) {
                CircularProgressIndicator()
            } else if (uiState.error) {
                Column {
                    Icon(imageVector = Ikona.Error, contentDescription = "error")
                    Text(text = "Error loading queue!")
                    TextButton(onClick = { viewModel.loadQueue() }) {
                        Text(text = "Retry")
                    }
                }
            } else {
                if (uiState.queue == null) {
                    Text(text = "No Queue. Something went wrong!")
                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        item {
                            Text(text = "Running [${uiState.queue.running.size}]:", style = MaterialTheme.typography.bodyLarge)
                        }

                        if(uiState.queue.running.isNotEmpty()){
                            items(uiState.queue.running) {
                                QueueRowUi(it, viewModel::cancelQueueWorkflow)
                                Spacer(modifier = Modifier.size(1.dp))
                            }
                        }else{
                            item {
                                Text(text = "Nothing running", style = MaterialTheme.typography.bodySmall)
                            }
                        }


                        item {
                            Text(text = "Pending [${uiState.queue.pending.size}]:", style = MaterialTheme.typography.bodyLarge)
                        }

                        if(uiState.queue.pending.isNotEmpty()){
                            items(uiState.queue.pending) {
                                QueueRowUi(it, viewModel::cancelQueueWorkflow)
                                Spacer(modifier = Modifier.size(1.dp))
                            }
                        }else{
                            item {
                                Text(text = "Nothing pending", style = MaterialTheme.typography.bodySmall)
                            }
                        }

                    }
                }
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun QueueRowUi(item: Queue.Workflow, onCancel:(Queue.Workflow) -> Unit) {
    Row (modifier = Modifier.fillMaxWidth()){
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = item.id)
            Text(text = item.prompt)
        }
        IconButton(onClick = {
            onCancel(item)
        }) {
            Icon(imageVector = Ikona.Cancel, contentDescription = "cancel", modifier = Modifier.size(24.dp))
        }
    }
}