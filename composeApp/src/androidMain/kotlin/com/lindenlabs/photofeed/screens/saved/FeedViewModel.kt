package com.lindenlabs.photofeed.screens.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindenlabs.photofeed.android.screens.search.domain.RecordSearchHistory
import domain.GetSearchResultViewEntities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import presentation.FeedScreenContract

//@HiltViewModel
internal class FeedViewModel (val getSearchResultViewEntities: GetSearchResultViewEntities, val recordSearchHistory: RecordSearchHistory) : ViewModel() {
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val mutableViewState =
        MutableStateFlow<FeedScreenContract.ViewState>(FeedScreenContract.ViewState.Loading)
    val viewState = mutableViewState

    init {
        refresh()
    }

    private fun refresh() {
        val queryHistoryItems = recordSearchHistory.getHistory()
        viewModelScope.launch(ioScope.coroutineContext) {
            val viewEntities = queryHistoryItems.toViewEntities()
            viewState.value = if(viewEntities.isEmpty()) {
                FeedScreenContract.ViewState.EmptyState
            } else {
                FeedScreenContract.ViewState.Content(viewEntities)
            }
        }
    }

    private suspend fun Set<String>.toViewEntities(): List<FeedScreenContract.ViewEntity> {
        return this.map {query ->
            val results = getSearchResultViewEntities(query)
            FeedScreenContract.ViewEntity(query, results)
        }
    }

}
