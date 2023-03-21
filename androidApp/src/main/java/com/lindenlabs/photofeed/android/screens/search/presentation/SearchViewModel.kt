package com.lindenlabs.photofeed.android.screens.search.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindenlabs.photofeed.android.screens.search.domain.RecordSearchHistory
import com.lindenlabs.photofeed.android.screens.search.domain.SearchScreenInteractor
import com.lindenlabs.photofeed.android.screens.search.presentation.SearchScreenContract.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val getSearchResultViewEntities: GetSearchResultViewEntities,
    private val recordSearchHistory: RecordSearchHistory
) : ViewModel() {
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val mutableViewState = MutableStateFlow<ViewState>(ViewState.Initial(""))
    val viewState = mutableViewState

    fun search(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch(ioScope.coroutineContext) {
                recordSearchHistory(query)
                runCatching { getSearchResultViewEntities(query) }
                    .onSuccess {
                        Log.e("SVM", "Testing success $it")
                        mutableViewState.value = ViewState.Initial(query, results = it, true) }
                    .onFailure {
                        Log.e("SVM", "Testing failed $it")
                    }
            }
        }
    }

    fun updateQuery(query: String) {
        mutableViewState.value = (mutableViewState.value as ViewState.Initial).copy(query = query)
    }
}
