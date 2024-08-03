package com.eltonkola.comfyflux.app

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.graphics.BitmapFactory
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun FluxAPIApp(
    modifier: Modifier,
    viewModel: ImageGenerationViewModel = viewModel(
        factory = ImageGenerationViewModelFactory(LocalContext.current.applicationContext)
    )

) {
    val uiState by viewModel.uiState.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Box(  modifier = Modifier.fillMaxSize()) {

        if (uiState.image != null) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    bitmap = uiState.image!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                IconButton(
                    onClick = { viewModel.setCurrentImage(null) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear"
                    )
                }
            }

        } else {


            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                TextField(
                    value = uiState.server,
                    onValueChange = { newText -> viewModel.updateSeverUrl(newText) },
                    label = { Text("Server IP and Port") },
                    placeholder = { Text("e.g., 192.168.1.1:8080") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )


                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = uiState.prompt,
                    onValueChange = { newText -> viewModel.updatePrompt(newText) },
                    label = { Text("Enter your prompt") },
                    placeholder = { Text("Enter your prompt") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp), // Adjust height as needed
                    maxLines = 6,
                    singleLine = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            if(!uiState.isLoading) {
                                viewModel.generateImages()
                            }
                        }
                    )
                )


                Button(
                    onClick = {
                        viewModel.generateImages()
                        keyboardController?.hide()
                    },
                    enabled = !uiState.isLoading
                ) {
                    Text(if (uiState.isLoading) "Loading..." else "Generate Images")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (uiState.error != null) {
                    Text("Error: ${uiState.error}")
                }

                if (uiState.images.isNotEmpty()) {
                    ImageGrid(images = uiState.images) {
                        viewModel.setCurrentImage(it)
                    }
                }
            }
        }
    }
}

@Composable
fun ImageGrid(images: Map<String, List<ByteArray>>, onZoom: (Bitmap) -> Unit) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        for ((nodeId, imageList) in images) {
            items(imageList.size) { index ->
                val imageData = imageList[index]
                var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }

                LaunchedEffect(imageData) {
                    bitmap = withContext(Dispatchers.Default) {
                        BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
                    }
                }

                bitmap?.let {

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(128.dp)
                    ) {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Generated image from node $nodeId",
                            modifier = Modifier
                                .fillMaxSize()
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton (
                                onClick = { saveImageToDownloads(context, it, "flux_image_${System.currentTimeMillis()}.png") }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Send,
                                    contentDescription = "Download"
                                )
                            }
                            IconButton(
                                onClick = { onZoom(it) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Zoom"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



fun saveImageToDownloads(context: Context, bitmap: Bitmap, filename: String) {
    val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    val file = File(directory, filename)

    try {
        FileOutputStream(file).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
        }
        Toast.makeText(context, "Image saved to downloads", Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
    }
}
