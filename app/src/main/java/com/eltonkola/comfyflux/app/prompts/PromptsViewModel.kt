package com.eltonkola.comfyflux.app.prompts


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eltonkola.comfyflux.app.data.PromptRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PromptsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PromptRepo(application)

    private val _pagingDataFlow = MutableStateFlow<PagingData<String>>(PagingData.empty())
    val pagingDataFlow: StateFlow<PagingData<String>> = _pagingDataFlow

    init {
        searchLines("")
    }

    fun searchLines(query: String) {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(pageSize = 50),
                pagingSourceFactory = { repository.getPagingSource(query) }
            ).flow.collectLatest {
                _pagingDataFlow.value = it
            }
        }
    }
}


