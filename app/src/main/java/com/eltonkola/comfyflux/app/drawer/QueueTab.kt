package com.eltonkola.comfyflux.app.drawer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eltonkola.comfyflux.app.MainViewModel
import com.eltonkola.comfyflux.app.model.Queue
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Cancel
import com.eltonkola.comfyflux.ui.theme.ikona.Error
import com.eltonkola.comfyflux.ui.theme.ikona.Refresh

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueueTab(viewModel: MainViewModel) {

    val uiState by viewModel.queueUiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if(uiState.queue == null){
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
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            if (uiState.loading) {
                CircularProgressIndicator()
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
                    Text(text = "No Queue. Something went wrong!")
                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        item {
                            Text(text = "Running [${uiState.queue!!.running.size}]:", style = MaterialTheme.typography.bodyLarge)
                        }

                        if(uiState.queue!!.running.isNotEmpty()){
                            items(uiState.queue!!.running) {
                                QueueRowUi(it, viewModel::cancelQueueWorkflow)
                                Spacer(modifier = Modifier.size(1.dp))
                            }
                        }else{
                            item {
                                Text(text = "Nothing running", style = MaterialTheme.typography.bodySmall)
                            }
                        }


                        item {
                            Text(text = "Pending [${uiState.queue!!.pending.size}]:", style = MaterialTheme.typography.bodyLarge)
                        }

                        if(uiState.queue!!.pending.isNotEmpty()){
                            items(uiState.queue!!.pending) {
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