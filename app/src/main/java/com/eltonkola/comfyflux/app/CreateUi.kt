package com.eltonkola.comfyflux.app

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.material3.Slider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
fun CreateUi(uiState: ImageGenerationUiState,
             viewModel: MainViewModel,
             openWorkflows:() -> Unit,
             openHistory:() -> Unit,
             ) {

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
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = uiState.workflow.name,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        openWorkflows()
                    },
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.8f),
            contentAlignment = Alignment.Center
            ){

            if (uiState.images.isNotEmpty()) {
                ImageGrid(images = uiState.images) {
                    viewModel.setCurrentImage(it)
                }
            }else{
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
            }
        }

        var tabIndex by remember { mutableIntStateOf(0) }

        val tabs = listOf("Prompt", "Size", "More")

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> PromptTab(uiState, viewModel)
                1 -> SizeTab(uiState, viewModel)
                2 -> MoreTab(uiState, viewModel)
            }
        }




        Spacer(modifier = Modifier.height(16.dp))


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


        if (uiState.error != null) {
            Text("Error: ${uiState.error}")
        }



        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(1f))

        Button(
            modifier = Modifier,
            onClick = {
                openHistory()
            },
            enabled = true
        ) {
            Text("Show History")
        }

    }
}

@Composable
fun PromptTab(uiState: ImageGenerationUiState, viewModel: MainViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
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
    }
}


@Composable
fun SizeTab(uiState: ImageGenerationUiState, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ImageSizeSelector(uiState = uiState, viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))

        ImageBatchSelector(uiState = uiState, viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))

    }
}


@Composable
fun MoreTab(uiState: ImageGenerationUiState, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

    }
}


@Composable
fun ImageSizeSelector(uiState: ImageGenerationUiState, viewModel: MainViewModel) {
    val imageSizes = listOf(256, 320, 384, 448, 512, 576, 640, 704, 768, 832, 896, 960, 1024)
    val sliderValues = List(imageSizes.size) { index -> index.toFloat() }

    var widthIndex by remember { mutableIntStateOf(imageSizes.indexOf(uiState.width)) }
    var heightIndex by remember { mutableIntStateOf(imageSizes.indexOf(uiState.height)) }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 6.dp)
        ) {
            Text(text = "Width: ${imageSizes[widthIndex]}Px")
            Slider(
                value = widthIndex.toFloat(),
                valueRange = sliderValues.first()..sliderValues.last(),
                onValueChange = {
                    widthIndex = it.toInt()
                    viewModel.setImageWidth(imageSizes[widthIndex])
                },
                steps = sliderValues.size
            )

            Text(text = "Height: ${imageSizes[heightIndex]}Px")
            Slider(
                value = heightIndex.toFloat(),
                valueRange = sliderValues.first()..sliderValues.last(),
                onValueChange = {
                    heightIndex = it.toInt()
                    viewModel.setImageHeight(imageSizes[heightIndex])
                },
                steps = sliderValues.size
            )
        }

        Box(
            modifier = Modifier
                .width(120.dp)
                .aspectRatio(imageSizes[widthIndex].toFloat() / imageSizes[heightIndex].toFloat())
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ){
            Text(text = "${imageSizes[widthIndex]}x${imageSizes[heightIndex]}Px")
        }



    }
}


@Composable
fun ImageBatchSelector(uiState: ImageGenerationUiState, viewModel: MainViewModel) {
    var imageBatch by remember { mutableIntStateOf(uiState.batchSize) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Text(text = "How many images do you want to generate: $imageBatch")
            Slider(
                value = imageBatch.toFloat(),
                valueRange = 1F..32F,
                onValueChange = {
                    imageBatch= it.toInt()
                    viewModel.setBatchSize(imageBatch)
                },
                steps = 32
            )
        }
}

