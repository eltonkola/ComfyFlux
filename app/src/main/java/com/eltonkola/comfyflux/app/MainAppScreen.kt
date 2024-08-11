package com.eltonkola.comfyflux.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eltonkola.comfyflux.app.drawer.HistoryAndQueue
import com.eltonkola.comfyflux.app.model.ProgressGenerationUIState
import com.eltonkola.comfyflux.app.prompts.PromptSearch
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Create
import com.eltonkola.comfyflux.ui.theme.ikona.History
import com.eltonkola.comfyflux.ui.theme.ikona.Menu
import com.eltonkola.comfyflux.ui.theme.ikona.Search
import com.eltonkola.comfyflux.ui.theme.ikona.Settings
import com.eltonkola.comfyflux.ui.theme.ikona.Stop
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(
    viewModel: MainViewModel,
    uiState: ImageGenerationUiState,
    progressUiState: ProgressGenerationUIState,
    navController: NavController
) {

    val sheetState = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }


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
                        navController.navigate(AppScreens.Settings.screenName)
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

                if(progressUiState.executing){
                    ExtendedFloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            viewModel.interruptImages()
                        }
                    ) {
                        Icon(
                            imageVector = Ikona.Stop,
                            contentDescription = "Stop",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = "Stop")
                    }
                }else{
                    ExtendedFloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                                viewModel.generateImages()
                                keyboardController?.hide()
                        },

                        ) {
                        Icon(
                            imageVector = Ikona.Create,
                            contentDescription = "Create",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = "Create")
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
                        CreateUi(uiState, progressUiState,  viewModel, navController) {
                            scope.launch {
                                drawerStateLeft.apply {
                                    if (isClosed) open() else close()
                                }
                            }
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
                HistoryAndQueue(viewModel, navController)
            }
        }
    }

}

