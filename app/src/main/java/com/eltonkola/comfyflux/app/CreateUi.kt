package com.eltonkola.comfyflux.app

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.eltonkola.comfyflux.R
import com.eltonkola.comfyflux.app.prompts.PromptSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun CreateUi(uiState: ImageGenerationUiState, viewModel: MainViewModel, openWorkflows:() -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ServerConnectionUi(viewModel = viewModel, uiState = uiState)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Default.MailOutline,
                contentDescription = null
            )
            Column (
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = uiState.workflow.name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = uiState.workflow.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Icon(
                modifier = Modifier.size(24.dp).clickable {
                    openWorkflows()
                },
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null)
        }
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
                    if (!uiState.isLoading) {
                        viewModel.generateImages()
                    }
                }
            ),
            leadingIcon = {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Rounded.List,
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
            },
            trailingIcon = {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                ) {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Prompts"
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.updatePrompt("")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            }
        )

        Row {


        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    viewModel.generateImages()
                    keyboardController?.hide()
                },
                enabled = !uiState.isLoading
            ) {
                Text(if (uiState.isLoading) "Loading..." else "Generate Images")
            }
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


@Composable
fun ImageGrid(images: Map<String, List<ByteArray>>, onZoom: (Bitmap) -> Unit) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
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
                            .size(160.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Generated image from node $nodeId",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
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
    val resolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/${context.getString(R.string.app_name)}")
    }

    try {
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            resolver.openOutputStream(it)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
                Toast.makeText(context, "Image saved to Pictures", Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(context, "Failed to open output stream", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(context, "Failed to insert image", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
    }
}