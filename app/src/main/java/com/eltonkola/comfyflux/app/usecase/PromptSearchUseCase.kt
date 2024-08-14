package com.eltonkola.comfyflux.app.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eltonkola.comfyflux.data.PromptRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

class PromptSearchUseCase(
    private val promptRepo: PromptRepo
) {

    private val _promptSearchPaging = MutableStateFlow<PagingData<String>>(PagingData.empty())
    val promptSearchPaging: StateFlow<PagingData<String>> = _promptSearchPaging

    suspend fun searchLines(query: String, viewModelScope: CoroutineScope) {
        Pager(
            config = PagingConfig(pageSize = 100),
            pagingSourceFactory = { promptRepo.getPagingSource(query) }
        ).flow.cachedIn(viewModelScope).collectLatest {
            _promptSearchPaging.value = it
        }
    }

}