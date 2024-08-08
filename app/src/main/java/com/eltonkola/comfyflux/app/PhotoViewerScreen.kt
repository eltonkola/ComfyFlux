package com.eltonkola.comfyflux.app

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eltonkola.comfyflux.R
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Back
import com.eltonkola.comfyflux.ui.theme.ikona.Download
import com.eltonkola.comfyflux.ui.theme.ikona.Share

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoViewerScreen(
    viewModel: MainViewModel,
    uiState: ImageGenerationUiState,
    navController: NavController
) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Gallery")
                },
                actions = {
                    IconButton(onClick = {
                        saveImageToDownloads(
                            context,
                            uiState.image!!,
                            "flux_image_${System.currentTimeMillis()}.png",
                            true
                        )

                    }) {
                        Icon(
                            imageVector = Ikona.Share,
                            contentDescription = "Share",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = {
                        saveImageToDownloads(
                            context,
                            uiState.image!!,
                            "flux_image_${System.currentTimeMillis()}.png",
                            false
                        )

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
    ) {

        Box(
            modifier = Modifier.padding(it),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                bitmap = uiState.image!!.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

        }
    }
}

private fun share(context: Context, uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "image/*"
    intent.putExtra(Intent.EXTRA_STREAM, uri)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    context.startActivity(Intent.createChooser(intent,"Share Image"))
}

fun saveImageToDownloads(context: Context, bitmap: Bitmap, filename: String, share: Boolean = false) {
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
                if(share){
                    share(context, uri)
                }
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
