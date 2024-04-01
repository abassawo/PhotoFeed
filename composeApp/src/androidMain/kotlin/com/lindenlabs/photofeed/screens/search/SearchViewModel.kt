package com.lindenlabs.photofeed.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.RecordSearchHistory
import domain.GetSearchResultViewEntities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import presentation.ImageResultViewEntity

object SearchScreenContract {

    sealed class ViewState {

        data class Initial(
            val query: String = "",
            val results: List<ImageResultViewEntity> = emptyList(),
            val isSearchActive: Boolean = false
        ) : ViewState()

        object SearchLoading : ViewState()

        data class Error(val throwable: Throwable) : ViewState()
    }
}

internal class SearchViewModel  constructor(
    private val getSearchResultViewEntities: GetSearchResultViewEntities,
    private val recordSearchHistory: RecordSearchHistory
) : ViewModel() {
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val mutableViewState = MutableStateFlow<SearchScreenContract.ViewState>(
        SearchScreenContract.ViewState.Initial("")
    )
    val viewState = mutableViewState

    fun search(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch(ioScope.coroutineContext) {
                recordSearchHistory(query)
                runCatching { getSearchResultViewEntities(query) }
                    .onSuccess {
                        Log.e("SVM", "Testing success $it")
                        mutableViewState.value =
                            SearchScreenContract.ViewState.Initial(query, results = it, true)
                    }
                    .onFailure {
                        Log.e("SVM", "Testing failed $it")
                    }
            }
        }
    }

    fun updateQuery(query: String) {
        mutableViewState.value = (mutableViewState.value as SearchScreenContract.ViewState.Initial).copy(query = query)
    }
}