package com.eltonkola.comfyflux.app.imageviwer

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.eltonkola.comfyflux.R
import com.eltonkola.comfyflux.app.MainViewModel
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Back
import com.eltonkola.comfyflux.ui.theme.ikona.Download
import com.eltonkola.comfyflux.ui.theme.ikona.Share
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PhotoViewerScreen(
    viewModel: MainViewModel,
    navController: NavController
) {

    val uiState by viewModel.imageUiState.collectAsState()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var currentImageUrl by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        PhotoZoomDialog(photoUrl = currentImageUrl) {
            showDialog = false
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Gallery")
                },
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            saveImageToDownloads(
                                context,
                                currentImageUrl,
                                "flux_image_${System.currentTimeMillis()}.png",
                                true
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Ikona.Share,
                            contentDescription = "Share",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = {
                        coroutineScope.launch {
                            saveImageToDownloads(
                                context,
                                currentImageUrl,
                                "flux_image_${System.currentTimeMillis()}.png",
                                false
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Ikona.Download,
                            contentDescription = "Save",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Ikona.Back,
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },
    ) { paddingValues ->

        val pagerState =
            rememberPagerState(initialPage = uiState.selected, pageCount = { uiState.images.size })

        HorizontalPager(
            state = pagerState,
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) { image ->

            LaunchedEffect(key1 = image) {
                currentImageUrl = uiState.images[image]
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = uiState.images[image],
                    contentDescription = "Image ${image + 1}",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { showDialog = true }
                )
            }

        }
    }
}

private fun share(context: Context, uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "image/*"
    intent.putExtra(Intent.EXTRA_STREAM, uri)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    context.startActivity(Intent.createChooser(intent, "Share Image"))
}

suspend fun saveImageToDownloads(
    context: Context,
    imageUrl: String,
    filename: String,
    share: Boolean = false
) {
    withContext(Dispatchers.IO) {
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            put(
                MediaStore.Images.Media.RELATIVE_PATH,
                "Pictures/${context.getString(R.string.app_name)}"
            )
        }

        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            val input: InputStream = connection.inputStream
            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            uri?.let {
                resolver.openOutputStream(it)?.use { outputStream ->
                    input.use { inputStream ->
                        inputStream.copyTo(outputStream)
                    }
                    outputStream.flush()

                    withContext(Dispatchers.Main) {
                        if (share) {
                            share(context, uri)
                        }
                        Toast.makeText(context, "Image saved to Pictures", Toast.LENGTH_SHORT)
                            .show()
                    }
                } ?: run {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Failed to open output stream", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } ?: run {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Failed to insert image", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
            }
        }
    }
}