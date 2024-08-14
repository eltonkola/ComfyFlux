package com.eltonkola.comfyflux.app.drawer

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eltonkola.comfyflux.app.MainViewModel
import com.eltonkola.comfyflux.app.components.LoadingUi
import com.eltonkola.comfyflux.app.model.Queue
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Cancel
import com.eltonkola.comfyflux.ui.theme.ikona.Error
import com.eltonkola.comfyflux.ui.theme.ikona.Refresh
import com.eltonkola.comfyflux.ui.theme.ikona.Stop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueueTab(viewModel: MainViewModel) {

    val uiState by viewModel.queueUiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (uiState.queue == null) {
            viewModel.loadQueue()
        }
    }

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
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if (uiState.loading) {
                LoadingUi(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp))
            } else if (uiState.error) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Ikona.Error, contentDescription = "error")
                    Text(text = "Error loading queue!")
                    TextButton(onClick = { viewModel.loadQueue() }) {
                        Text(text = "Retry")
                    }
                }
            } else {
                if (uiState.queue == null) {
                    Text(
                        text = "No Queue. Something went wrong!",
                        modifier = Modifier.padding(6.dp)
                    )
                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        item {
                            Text(
                                text = "Running [${uiState.queue!!.running.size}]:",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.secondary)
                                    .padding(8.dp),
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        }

                        if (uiState.queue!!.running.isNotEmpty()) {
                            items(uiState.queue!!.running) { item ->
                                QueueRowUi(item, true) { viewModel.interruptImages() }
                                Spacer(modifier = Modifier.size(1.dp))
                            }
                        } else {
                            item {
                                Text(
                                    text = "Nothing running",
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                        }


                        item {
                            Text(
                                text = "Pending [${uiState.queue!!.pending.size}]:",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.secondary)
                                    .padding(8.dp),
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        }

                        if (uiState.queue!!.pending.isNotEmpty()) {
                            items(uiState.queue!!.pending) {
                                QueueRowUi(it, false, viewModel::cancelQueueWorkflow)
                                Spacer(modifier = Modifier.size(2.dp))
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(MaterialTheme.colorScheme.secondary)
                                )
                            }
                        } else {
                            item {
                                Text(
                                    text = "Nothing pending",
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(6.dp)
                                )
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
fun QueueRowUi(item: Queue.Workflow, isRunning: Boolean, onCancel: (Queue.Workflow) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = item.id, fontSize = 10.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = item.prompt, fontSize = 16.sp)
        }
        IconButton(onClick = {
            onCancel(item)
        }) {
            Icon(
                imageVector = if (isRunning) Ikona.Stop else Ikona.Cancel,
                contentDescription = "cancel",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}