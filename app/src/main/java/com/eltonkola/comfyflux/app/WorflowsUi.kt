package com.eltonkola.comfyflux.app

import android.widget.Space
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eltonkola.comfyflux.app.model.Workflow
import com.eltonkola.comfyflux.app.model.workflows

@Composable
fun WorkflowsUi(onSelect: (Workflow) -> Unit, uiState: ImageGenerationUiState) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),) {
        Text(
            text = "Select a workflow to generate images with.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Make sure FLUX models are installed on your sever, and everything works on comfyUi itself, this app is just a ui utility on top of it.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(8.dp))
        LazyColumn {
            items(workflows){
                WorkflowRow(it, uiState.workflow == it, onSelect)
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
    }
}

@Composable
fun WorkflowRow(workflow: Workflow, selected: Boolean,  onSelect: (Workflow) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth()
            .border(
                width = if(selected) 1.dp else 0.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable {
            onSelect(workflow)
        }
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = Icons.Default.MailOutline,
            contentDescription = null
        )
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = workflow.name,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = workflow.description,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null)
    }
}