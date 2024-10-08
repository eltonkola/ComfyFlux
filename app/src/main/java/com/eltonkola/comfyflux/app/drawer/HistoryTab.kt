package com.eltonkola.comfyflux.app.drawer

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.eltonkola.comfyflux.app.AppScreens
import com.eltonkola.comfyflux.app.MainViewModel
import com.eltonkola.comfyflux.components.LoadingUi
import com.eltonkola.comfyflux.data.model.HistoryItem
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Copy
import com.eltonkola.comfyflux.ui.theme.ikona.Delete
import com.eltonkola.comfyflux.ui.theme.ikona.Error
import com.eltonkola.comfyflux.ui.theme.ikona.Image
import com.eltonkola.comfyflux.ui.theme.ikona.Refresh

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryTab(viewModel: MainViewModel, navController: NavController) {

    val uiState by viewModel.historyUiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (uiState.history.isEmpty()) {
            viewModel.loadHistory()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All images on server") },
                actions = {
                    if (uiState.silentLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp)
                        )
                    }
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
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if (uiState.loading) {
                LoadingUi(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
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

                        items(uiState.history) { row ->
                            HistoryRowUi(row, openImage = { images, index ->
                                viewModel.viewImage(images, index)
                                navController.navigate(AppScreens.ImageViewer.screenName)

                            },
                                onDelete = {
                                    viewModel.deleteHistory(row.id)
                                }
                            )
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
fun HistoryRowUi(item: HistoryItem, openImage: (List<String>, Int) -> Unit, onDelete: () -> Unit) {
    val clipboardManager = LocalClipboardManager.current
    Column {
        Text(text = "Nr: ${item.images.size} success: ${item.success} - completed: ${item.completed}")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            if (item.images.isNotEmpty()) {
                items(item.images) { image ->
                    AsyncImage(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                openImage(item.images, item.images.indexOf(image))
                            }
                            .background(MaterialTheme.colorScheme.secondary),
                        model = image,
                        contentDescription = null,
                    )
                }
            } else {
                item {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.secondary),
                        contentAlignment = Alignment.Center
                    ) {
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
                        text = item.prompt.getPrompt(),
                        fontSize = 10.sp
                    )
                    Column {


                        IconButton(onClick = {
                            clipboardManager.setText(AnnotatedString(item.prompt.getPrompt()))
                        }) {
                            Icon(
                                imageVector = Ikona.Copy,
                                contentDescription = "copy",
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        IconButton(onClick = {
                            onDelete()
                        }) {
                            Icon(
                                imageVector = Ikona.Delete,
                                contentDescription = "delete",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}