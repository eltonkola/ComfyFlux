package com.eltonkola.comfyflux.app

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun ImageGrid(images: Map<String, List<ByteArray>>, onZoom: (Bitmap) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        for ((nodeId, imageList) in images) {
            items(imageList.size) { index ->
                val imageData = imageList[index]
                var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }

                LaunchedEffect(imageData) {
                    bitmap = withContext(Dispatchers.Default) {
                        BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
                    }
                }

                bitmap?.let {

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(160.dp)
                            .clickable {
                                onZoom(it)
                            }
                        ,
                    ) {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Generated image from node $nodeId",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                    }
                }
            }
        }
    }
}
