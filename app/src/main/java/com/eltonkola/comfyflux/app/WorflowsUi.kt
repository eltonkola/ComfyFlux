package com.eltonkola.comfyflux.app

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eltonkola.comfyflux.app.model.WorkflowFile
import com.eltonkola.comfyflux.app.model.workflows
import com.eltonkola.comfyflux.ui.theme.Ikona
import com.eltonkola.comfyflux.ui.theme.ikona.ArrowRight
import com.eltonkola.comfyflux.ui.theme.ikona.WorkflowIc

@Composable
fun WorkflowsUi(onSelect: (WorkflowFile) -> Unit, uiState: ImageGenerationUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
        Text(
            text = "Workflows",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Select a workflow to generate images with.",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.size(8.dp))
        LazyColumn {
            items(workflows){
                WorkflowRow(it, uiState.workflow == it, onSelect)
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = "Your workflows",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Manually or with other apps (drive/dropbox, etc) sync your comfyUi api workflows, and select it from below.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(8.dp))


        LocalWorkflows()


    }
}


@Composable
fun WorkflowRow(workflow: WorkflowFile, selected: Boolean, onSelect: (WorkflowFile) -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (selected) 2.dp else 0.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onSelect(workflow)
            }
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp),
            imageVector = Ikona.WorkflowIc,
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
            imageVector = Ikona.ArrowRight,
            contentDescription = null)
    }
}