package com.eltonkola.comfyflux.app

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eltonkola.comfyflux.R
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Back
import com.eltonkola.comfyflux.ui.theme.ikona.Key
import com.eltonkola.comfyflux.ui.theme.ikona.Paste

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: MainViewModel,
    navController: NavController
) {


    val clipboardManager = LocalClipboardManager.current
    val uiState by viewModel.settingsState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Settings")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Ikona.Back, contentDescription = "Back", modifier = Modifier.size(24.dp))
                    }
                }
            )
        }
    ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp),
            ) {

                Text(
                    text = "System theme: ${if (uiState.system) "On" else "Off"}",
                    fontSize = 20.sp
                )
                Switch(
                    checked = uiState.system,
                    onCheckedChange = {
                        viewModel.updateSystemTheme(it)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                if(!uiState.system) {
                    Text(
                        text = "Dark Mode: ${if (uiState.dark) "On" else "Off"}",
                        fontSize = 20.sp
                    )
                    Switch(
                        checked = uiState.dark,
                        onCheckedChange = {
                            viewModel.updateDarkTheme(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                //dynamic colors are on android 12+
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    Text(
                        text = "Dynamic color: ${if (uiState.dynamicColor) "On" else "Off"}",
                        fontSize = 20.sp
                    )
                    Switch(
                        checked = uiState.dynamicColor,
                        onCheckedChange = {
                            viewModel.updateDynamicColor(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }



                Text(
                    text = "GROQ Api Key",
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "If you use a wrong api key, calls will silently fail.",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        label = {
                            Text(text = "GROQ API KEY")
                        },
                          supportingText = {
                              Text(text = "Leave empty for the default one")
                          },      
                        value = uiState.grqApiKey,
                        onValueChange = {
                            viewModel.setGrqApiKey(it)
                        },
                        readOnly = true,
                        maxLines = 2,
                        textStyle = TextStyle(fontSize = 10.sp),
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton({
                                clipboardManager.getText()?.let{
                                    viewModel.setGrqApiKey(it.text)
                                }
                            }) {
                                Icon(imageVector = Ikona.Paste, contentDescription = "Paste", modifier = Modifier.size(24.dp))
                            }
                        },
                        leadingIcon = {
                            Icon(imageVector = Ikona.Key, contentDescription = "Key", modifier = Modifier.size(24.dp))
                        }
                    )

                
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f))
                val context = LocalContext.current
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo), contentDescription = "logo")
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = stringResource(id = R.string.about_version, context.getVersionNameAndCode()),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = stringResource(id = R.string.about_body),
                        style = MaterialTheme.typography.labelSmall
                    )
                    val link = stringResource(id = R.string.about_link)
                    TextButton(onClick = { context.openLinkInBrowser(link) }) {
                        Text(text = link)
                    }

                }

            }

    }

}

fun Context.getVersionNameAndCode(): String {
    val packageInfo: PackageInfo? = try {
        packageManager.getPackageInfo(packageName, 0)
    } catch (e: PackageManager.NameNotFoundException)
    {
        null
    }

    return packageInfo?.let {
        "${it.versionName} (${it.longVersionCode})"
    } ?: ""
}


fun Context.openLinkInBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}
