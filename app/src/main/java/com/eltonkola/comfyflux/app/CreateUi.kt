package com.eltonkola.comfyflux.app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.ArrowDown
import com.eltonkola.comfyflux.ui.theme.ikona.Clean
import com.eltonkola.comfyflux.ui.theme.ikona.Image
import com.eltonkola.comfyflux.ui.theme.ikona.Magic
import com.eltonkola.comfyflux.ui.theme.ikona.Paste
import com.eltonkola.comfyflux.ui.theme.ikona.Promp
import com.eltonkola.comfyflux.ui.theme.ikona.Refresh
import com.eltonkola.comfyflux.ui.theme.ikona.Seed
import com.eltonkola.comfyflux.ui.theme.ikona.Workflow

@Composable
fun CreateUi(
    uiState: ImageGenerationUiState,
    viewModel: MainViewModel,
    navController: NavController,
    openWorkflows: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ServerConnectionUi(viewModel = viewModel, uiState = uiState)
        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.stats == null) {
            Text(text = "Please make sure your server in online, and in the same network, to be able to use this app.\nRemember, this us just a minimal front ent for simple flows.")

        } else {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clip(RoundedCornerShape(6.dp))
                    .clickable {
                        openWorkflows()
                    }
                    .padding(8.dp)
            ) {

                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Ikona.Workflow,
                    contentDescription = null
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Workflow: ${uiState.workflow.name}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Ikona.ArrowDown,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.8f)
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {

                if (uiState.images.isNotEmpty()) {
                    ImageGrid(images = uiState.images) {
                        viewModel.setCurrentImage(it)
                        navController.navigate(AppScreens.ImageViewer.screenName)
                    }
                } else {
                    Icon(
                        imageVector = Ikona.Image,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }

                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(64.dp)
                    )
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

            if (uiState.error != null) {
                Text("Error: ${uiState.error}")
            }

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
    ) {
        TextField(
            value = uiState.prompt,
            onValueChange = { newText -> viewModel.updatePrompt(newText) },
            label = { Text("Enter your prompt") },
            placeholder = { Text("Enter your prompt") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp), // Adjust height as needed
            minLines = 6,
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
                        imageVector = Ikona.Promp,
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

                    val clipboard = LocalClipboardManager.current

                    IconButton(
                        onClick = {
                            viewModel.enrichPrompt()
                        }
                    ) {
                        Icon(
                            imageVector = Ikona.Magic,
                            contentDescription = "Ai generate prompt",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            clipboard.getText()?.let{
                                viewModel.updatePrompt(uiState.prompt + it)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Ikona.Paste,
                            contentDescription = "paste",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            viewModel.updatePrompt("")
                        }
                    ) {
                        Icon(
                            imageVector = Ikona.Clean,
                            contentDescription = "Clear",
                            modifier = Modifier.size(24.dp)
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

    }
}


@Composable
fun MoreTab(uiState: ImageGenerationUiState, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        ImageBatchSelector(uiState = uiState, viewModel = viewModel)
        Spacer(modifier = Modifier.height(16.dp))

        SeedSelector(uiState = uiState, viewModel = viewModel)

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
        Box(modifier = Modifier.size(120.dp, 120.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .defaultMinSize(40.dp, 40.dp)
                    .aspectRatio(imageSizes[widthIndex].toFloat() / imageSizes[heightIndex].toFloat())
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${imageSizes[widthIndex]}x${imageSizes[heightIndex]}Px",
                    color = MaterialTheme.colorScheme.onPrimary
                    )
            }
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
        Text(
            text = "Image Batch",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(text = "How many images do you want to generate: $imageBatch")
        Slider(
            value = imageBatch.toFloat(),
            valueRange = 1F..32F,
            onValueChange = {
                imageBatch = it.toInt()
                viewModel.setBatchSize(imageBatch)
            },
            steps = 32
        )
    }
}


@Composable
fun SeedSelector(uiState: ImageGenerationUiState, viewModel: MainViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        Text(
            text = "Seed to use",
            style = MaterialTheme.typography.headlineSmall
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Use random seed")
            Checkbox(
                checked = uiState.isRandom,
                onCheckedChange = {
                    viewModel.setSeed(uiState.seed, it)
                }
            )
        }

        TextField(
            enabled = !uiState.isRandom,
            modifier = Modifier.fillMaxWidth(),
            value = uiState.seed.toString(),
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    viewModel.setSeed(it.toLong(), uiState.isRandom)
                }
            },
            label = { Text("Seed value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                IconButton(onClick = {
                    if (!uiState.isRandom) {
                        viewModel.randomSeed()
                    }
                }) {
                    Icon(
                        imageVector = Ikona.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                        )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Ikona.Seed,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
    }
}

