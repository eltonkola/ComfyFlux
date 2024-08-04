package com.eltonkola.comfyflux.app.prompts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.LazyPagingItems
//import androidx.paging.compose.items

@Composable
fun PromptSearch(viewModel: PromptsViewModel = viewModel(), onPromptSelected:(String) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val lines = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        BasicTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.searchLines(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    if (searchQuery.isEmpty()) {
                        Text("Search...", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                    }
                    innerTextField()
                }
            }
        )

        LazyColumn(modifier = Modifier
            .weight(1f)
            .padding(8.dp)) {
//            items(lines) { line ->
//                Text(line ?: "")
//            }
            items(lines.itemSnapshotList.items) { line ->
                Row (
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                        onPromptSelected(line)
                    }
                ){
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
