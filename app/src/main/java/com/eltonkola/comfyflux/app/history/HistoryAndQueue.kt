package com.eltonkola.comfyflux.app.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eltonkola.comfyflux.app.MoreTab
import com.eltonkola.comfyflux.app.PromptTab
import com.eltonkola.comfyflux.app.SizeTab
import java.util.Queue

@Composable
fun HistoryAndQueue(
    viewModel: HistoryQueueViewModel = viewModel(),
    onClose: () -> Unit
) {

    val historyUiState by viewModel.historyUiState.collectAsState()

        var tabIndex by remember { mutableIntStateOf(0) }

        val tabs = listOf("History", "Queue")


        Column(modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 360.dp)
            .padding(8.dp)) {

            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> HistoryTab(viewModel, historyUiState)
                1 -> QueueTab()
            }
        }


}