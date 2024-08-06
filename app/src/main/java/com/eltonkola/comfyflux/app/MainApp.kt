package com.eltonkola.comfyflux.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class AppScreens(val screenName: String){
    Main("mainScreen"),
    ImageViewer("imageViewerScreen"),
    Settings("settingsScreen");
}

@Composable
fun MainApp(
    viewModel: MainViewModel = viewModel(
        factory = ImageGenerationViewModelFactory(LocalContext.current.applicationContext)
    )
) {

    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.Main.screenName) {
        composable(AppScreens.Main.screenName) {
            MainAppScreen(viewModel, uiState, navController)
        }
        composable(AppScreens.ImageViewer.screenName)
        {
            PhotoViewerScreen(viewModel, uiState, navController)
        }
        composable(AppScreens.Settings.screenName) {
            SettingsScreen(viewModel, uiState, navController)
        }
    }

}
