package com.eltonkola.comfyflux.app.drawer

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.eltonkola.comfyflux.app.MainViewModel
import com.eltonkola.comfyflux.app.model.HistoryItem
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Copy
import com.eltonkola.comfyflux.ui.theme.ikona.Error
import com.eltonkola.comfyflux.ui.theme.ikona.Image
import com.eltonkola.comfyflux.ui.theme.ikona.Refresh

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryTab( viewModel: MainViewModel) {

    val uiState by viewModel.historyUiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if(uiState.history.isEmpty()) {
            viewModel.loadHistory()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All images on server") },
                actions = {
                    IconButton(onClick = { viewModel.loadHistory() }) {
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
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun HistoryRowUi(item: HistoryItem) {
    val clipboardManager = LocalClipboardManager.current
        Column {
            Text(text = "Nr: ${item.images.size} success: ${item.success} - completed: ${item.completed}")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if(item.images.isNotEmpty()) {
                    items(item.images) {
                        AsyncImage(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(MaterialTheme.colorScheme.secondary),
                            model = item.images.first(),
                            contentDescription = null,
                        )
                    }
                }else{
                 item{
                  Box(modifier = Modifier.size(120.dp)
                      .clip(RoundedCornerShape(10.dp))
                      .background(MaterialTheme.colorScheme.secondary),
                      contentAlignment = Alignment.Center
                  ){
                      Icon(
                          imageVector = Ikona.Image,
                          contentDescription = "failed",
                          tint = MaterialTheme.colorScheme.onSecondary
                      )
                  }
                 }
                }
                item {
                    Row {
                        Text(
                            modifier = Modifier.width(240.dp),
                            text = item.prompt,
                            fontSize = 10.sp
                        )
                        IconButton(onClick = {
                            clipboardManager.setText(AnnotatedString(item.prompt))
                        }) {
                            Icon(imageVector = Ikona.Copy, contentDescription = "copy", modifier = Modifier.size(24.dp))
                        }
                    }
                }
            }
        }
}