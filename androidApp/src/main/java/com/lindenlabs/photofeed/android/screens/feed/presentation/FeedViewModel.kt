package com.lindenlabs.photofeed.android.screens.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindenlabs.photofeed.android.screens.feed.FeedScreenContract
import com.lindenlabs.photofeed.android.screens.search.domain.RecordSearchHistory
import com.lindenlabs.photofeed.android.screens.search.presentation.GetSearchResultViewEntities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FeedViewModel @Inject constructor(val getSearchResultViewEntities: GetSearchResultViewEntities, val recordSearchHistory: RecordSearchHistory) : ViewModel() {
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val mutableViewState =
        MutableStateFlow<FeedScreenContract.ViewState>(FeedScreenContract.ViewState.Loading)
    val viewState = mutableViewState

    init {
      refresh()
    }

    fun refresh() {
        val queryHistoryItems = recordSearchHistory.getHistory()
        viewModelScope.launch(ioScope.coroutineContext) {
            val viewEntities = queryHistoryItems.toViewEntities()
            viewState.value = FeedScreenContract.ViewState.Content(viewEntities)
        }
    }

    private suspend fun Set<String>.toViewEntities(): List<FeedScreenContract.ViewEntity> {
        return this.map {query ->
            val results = getSearchResultViewEntities(query)
            FeedScreenContract.ViewEntity(query, results)
        }
    }

}


