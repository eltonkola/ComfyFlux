package com.eltonkola.comfyflux.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eltonkola.comfyflux.app.imageviwer.PhotoViewerScreen
import com.eltonkola.comfyflux.app.settings.SettingsScreen
import com.eltonkola.comfyflux.ui.theme.ComfyFluxTheme
import org.koin.androidx.compose.koinViewModel

enum class AppScreens(val screenName: String) {
    Main("mainScreen"),
    ImageViewer("imageViewerScreen"),
    Settings("settingsScreen");
}

@Composable
fun MainApp(
    viewModel: MainViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val progressUiState by viewModel.progressUiState.collectAsState()
    val navController = rememberNavController()

    val settings by viewModel.settingsState.collectAsState()

    val isDark = if (settings.system) {
        isSystemInDarkTheme()
    } else {
        settings.dark
    }
    ComfyFluxTheme(
        darkTheme = isDark,
        dynamicColor = settings.dynamicColor
    ) {

        NavHost(navController = navController, startDestination = AppScreens.Main.screenName) {
            composable(AppScreens.Main.screenName) {
                MainAppScreen(viewModel, uiState, progressUiState, navController)
            }
            composable(AppScreens.ImageViewer.screenName)
            {
                PhotoViewerScreen(viewModel, navController)
            }
            composable(AppScreens.Settings.screenName) {
                SettingsScreen(viewModel, navController)
            }
        }
    }
}
