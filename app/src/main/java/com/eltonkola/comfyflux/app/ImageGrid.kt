package com.eltonkola.comfyflux.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun ImageGrid(images: List<String>, onZoom: (String) -> Unit) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(images.size) { index ->

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(160.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        onZoom(images[index])
                    },
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(2.dp))
                        .background(MaterialTheme.colorScheme.secondary),
                    model = images[index],
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

            }
        }

    }
}
