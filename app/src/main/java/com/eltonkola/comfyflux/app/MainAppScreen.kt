package com.eltonkola.comfyflux.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eltonkola.comfyflux.app.prompts.PromptSearch
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


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerStateRight,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        PromptSearch {
                            viewModel.updatePrompt(it)
                            scope.launch {
                                drawerStateRight.apply {
                                    if (!isClosed) close()
                                }
                            }
                        }
                    }
                }
            },

            ) {

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                ModalNavigationDrawer(
                    drawerState = drawerStateLeft,
                    drawerContent = {
                        ModalDrawerSheet(
                            modifier = Modifier.fillMaxWidth(0.8f)
                        ) {  Text(text = "Left") }
                    },

                    ) {



                    Scaffold(
                        floatingActionButton = {
                            ExtendedFloatingActionButton(
                                text = { Text("Show History") },
                                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                                onClick = {
                                    showBottomSheet = true
                                }
                            )
                        },
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
                                                imageVector = Icons.Filled.Menu,
                                                contentDescription = "workflows"
                                            )
                                        }
                                },
                                title = {
                                    Text(text = "ComfyFlux")
                                },
                                actions = {

                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerStateRight.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Search,
                                            contentDescription = "prompts"
                                        )
                                    }
                                }
                            )

                        },

                        ) { contentPadding ->

                        Column(
                            modifier = Modifier.padding(contentPadding)
                        ) {
                            if(uiState.stats != null) {
                                if (uiState.image != null) {
                                    ImagePreviewUi(image = uiState.image!!) {
                                        viewModel.setCurrentImage(null)
                                    }
                                } else {
                                    CreateUi(uiState, viewModel)
                                }
                            }else{
                                Text(text = "Please make sure your server in online, and in the same network, to be able to use this app.\nRemember, this us just a minimal front ent for simple flows.")
                            }
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
            }
        }
    }

}
