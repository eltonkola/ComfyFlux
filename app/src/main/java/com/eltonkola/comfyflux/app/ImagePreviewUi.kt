package com.eltonkola.comfyflux.app

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.eltonkola.comfyflux.R
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Close
import com.eltonkola.comfyflux.ui.theme.ikona.Download
import com.eltonkola.comfyflux.ui.theme.ikona.Share
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun ImagePreviewUi(image: Bitmap, onClose:() -> Unit) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    BackHandler {
        scope.launch {
            onClose()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )


        ImageViewerActionBar(
           modifier = Modifier,
           onShareClick = {
               saveImageToDownloads(context, image, "flux_image_${System.currentTimeMillis()}.png", true)
           },
           onSaveClick = {
               saveImageToDownloads(context, image, "flux_image_${System.currentTimeMillis()}.png")
           },
           onCloseClick = {
               onClose()
           }
        )

    }
}

@Composable
fun ImageViewerActionBar(
    modifier: Modifier = Modifier,
    onShareClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
        modifier = modifier.padding(end = 16.dp, bottom = 8.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondary,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(onClick = onShareClick) {
                Icon(imageVector = Ikona.Share, contentDescription = "Share", tint = MaterialTheme.colorScheme.onSecondary)
            }
            IconButton(onClick = onSaveClick) {
                Icon(imageVector = Ikona.Download, contentDescription = "Save", tint = MaterialTheme.colorScheme.onSecondary)
            }
            IconButton(onClick = onCloseClick) {
                Icon(imageVector = Ikona.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSecondary)
            }
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
