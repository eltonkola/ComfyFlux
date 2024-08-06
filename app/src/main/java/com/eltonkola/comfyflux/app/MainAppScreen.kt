package com.eltonkola.comfyflux.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eltonkola.comfyflux.app.prompts.PromptSearch
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Create
import com.eltonkola.comfyflux.ui.theme.ikona.History
import com.eltonkola.comfyflux.ui.theme.ikona.Menu
import com.eltonkola.comfyflux.ui.theme.ikona.Search
import com.eltonkola.comfyflux.ui.theme.ikona.Settings
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(
    viewModel: MainViewModel = viewModel(
        factory = ImageGenerationViewModelFactory(LocalContext.current.applicationContext)
    )
) {

    val uiState by viewModel.uiState.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    val drawerStateLeft = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerStateRight = rememberDrawerState(initialValue = DrawerValue.Closed)
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerStateLeft.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Ikona.Menu,
                            contentDescription = "workflows",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                title = {
                    Text(text = "ComfyFlux")
                },
                actions = {

                    IconButton(onClick = {
                        //TODO - open settings
                    }) {
                        Icon(
                            imageVector = Ikona.Settings,
                            contentDescription = "settings",
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    IconButton(onClick = {
                        scope.launch {
                            drawerStateRight.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Ikona.Search,
                            contentDescription = "prompts",
                            modifier = Modifier.size(24.dp)
                        )
                    }

                }
            )

        },
        floatingActionButton = {
            if(uiState.image == null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        onClick = {
                            showBottomSheet = true
                        },
                        modifier = Modifier.width(120.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Ikona.History,
                                contentDescription = "History",
                                modifier = Modifier.size(24.dp)
                            )
                            Text(text = "History")

                        }
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    ExtendedFloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            if (!uiState.isLoading) {
                                viewModel.generateImages()
                                keyboardController?.hide()
                            }
                        },

                        ) {
                        Icon(
                            imageVector = Ikona.Create,
                            contentDescription = "Create",
                            modifier = Modifier.size(24.dp)
                        )

                        if (uiState.isLoading) {
                            Text(text = "Generating...")
                        } else {
                            Text(text = "Create")
                        }
                    }
                }
            }
        }
    ) { contentPadding ->

        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            DoubleDrawerUi(
                drawerStateLeft = drawerStateLeft,
                drawerStateRight = drawerStateRight,
                mainContent = {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (uiState.stats != null) {
                            if (uiState.image != null) {
                                ImagePreviewUi(image = uiState.image!!) {
                                    viewModel.setCurrentImage(null)
                                }
                            } else {
                                CreateUi(uiState, viewModel) {
                                    scope.launch {
                                        drawerStateLeft.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(text = "Please make sure your server in online, and in the same network, to be able to use this app.\nRemember, this us just a minimal front ent for simple flows.")
                        }
                    }
                },
                rightContent = {
                    PromptSearch {
                        viewModel.updatePrompt(it)
                        scope.launch {
                            drawerStateRight.apply {
                                if (!isClosed) close()
                            }
                        }
                    }
                },
                leftContent = {
                    WorkflowsUi(
                        uiState = uiState,
                        onSelect = {
                            viewModel.updateWorkflow(it)
                            scope.launch {
                                drawerStateLeft.apply {
                                    if (!isClosed) close()
                                }
                            }
                        }
                    )
                }
            )
        }


        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                HistoryUi(contentPadding) {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }

            }
        }
    }

}

