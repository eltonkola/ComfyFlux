package com.eltonkola.comfyflux.app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class FolderViewModel(application: Application) : AndroidViewModel(application) {
    private val _files = MutableStateFlow<List<DocumentFile>>(emptyList())
    val files = _files.asStateFlow()

    private val _selectedFolder = MutableStateFlow<Uri?>(null)
    val selectedFolder = _selectedFolder.asStateFlow()

    private val _hasPermission = MutableStateFlow(false)
    val hasPermission = _hasPermission.asStateFlow()

    init {
        loadSavedFolder()
    }

    fun loadFiles(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("FolderViewModel", "Loading files from URI: $uri")

            // Take persistent permission immediately
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            getApplication<Application>().contentResolver.takePersistableUriPermission(uri, takeFlags)

            if (checkPermission(uri)) {
                Log.d("FolderViewModel", "Permission granted for URI: $uri")
                val documentsTree = DocumentFile.fromTreeUri(getApplication(), uri)
                val fileList = documentsTree?.listFiles()?.toList() ?: emptyList()
                Log.d("FolderViewModel", "Found ${fileList.size} files")
                _files.value = fileList
                _selectedFolder.value = uri
                _hasPermission.value = true
                saveSelectedFolderUri(uri)
            } else {
                Log.d("FolderViewModel", "Permission denied for URI: $uri")
                _hasPermission.value = false
                _selectedFolder.value = null
                _files.value = emptyList()
            }
        }
    }

    private fun saveSelectedFolderUri(uri: Uri) {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("FolderPrefs", Application.MODE_PRIVATE)
        sharedPreferences.edit().putString("SELECTED_FOLDER_URI", uri.toString()).apply()
    }

    private fun loadSavedFolder() {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("FolderPrefs", Application.MODE_PRIVATE)
        val savedUriString = sharedPreferences.getString("SELECTED_FOLDER_URI", null)
        savedUriString?.let {
            val uri = Uri.parse(it)
            if (checkPermission(uri)) {
                loadFiles(uri)
            } else {
                _hasPermission.value = false
                _selectedFolder.value = null
                _files.value = emptyList()
            }
        }
    }

    private fun checkPermission(uri: Uri): Boolean {
        val list = getApplication<Application>().contentResolver.persistedUriPermissions
        val hasPermission = list.any { it.uri == uri && it.isReadPermission }
        Log.d("FolderViewModel", "Checking permission for URI: $uri, result: $hasPermission")
        return hasPermission
    }

    fun requestNewFolder() {
        _selectedFolder.value = null
        _files.value = emptyList()
        _hasPermission.value = false
    }
}


@Composable
fun FolderScreen(viewModel: FolderViewModel = viewModel()) {
    val files by viewModel.files.collectAsState()
    val selectedFolder by viewModel.selectedFolder.collectAsState()
    val hasPermission by viewModel.hasPermission.collectAsState()

    val folderPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree()
    ) { uri: Uri? ->
        uri?.let { viewModel.loadFiles(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                if (hasPermission) {
                    viewModel.requestNewFolder()
                }
                folderPicker.launch(null)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(if (hasPermission) "Change Folder" else "Select Folder")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (hasPermission) {
            Text("Selected Folder: $selectedFolder")
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(files) { file ->
                    Text(file.name ?: "Unnamed File")
                    Divider()
                }
            }
        } else {
            Text("No folder selected or permission denied. Please select a folder.")
        }
    }
}

@Composable
fun LocalWorkflows(){
    FolderScreen()
}


