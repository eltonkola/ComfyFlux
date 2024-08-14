package com.eltonkola.comfyflux.app.prompts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.eltonkola.comfyflux.app.MainViewModel
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.Clean
import com.eltonkola.comfyflux.ui.theme.ikona.Search

@Composable
fun PromptSearch(viewModel: MainViewModel, onPromptSelected: (String) -> Unit) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val lines = viewModel.promptSearchPaging.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Prompt search",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Search oje of the 20k+ prompts, looking for inspiration",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.size(8.dp))


        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.searchLines(it.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            label = { Text(text = "Prompt Search") },
            leadingIcon = {
                Icon(
                    imageVector = Ikona.Search,
                    contentDescription = "Clear",
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        searchQuery = TextFieldValue("")
                        viewModel.searchLines("")
                    }
                ) {
                    Icon(
                        imageVector = Ikona.Clean,
                        contentDescription = "Clear",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
//            items(lines) { line ->
//                Text(line ?: "")
//            }
            items(lines.itemSnapshotList.items) { line ->
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onPromptSelected(line)
                        }
                ) {
                    Text(
                        text = line,
                        fontSize = 12.sp
                    )
                }
            }
            if (lines.loadState.append is androidx.paging.LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Loading more...")
                    }
                }
            }
            if (lines.loadState.append is androidx.paging.LoadState.Error) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Error loading more")
                    }
                }
            }
        }
    }
}
