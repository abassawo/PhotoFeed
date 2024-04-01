package presentation

import androidx.compose.runtime.mutableStateOf
import com.lindenlabs.photofeed.android.screens.search.domain.RecordSearchHistory
import domain.GetSearchResultViewEntities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object FeedScreenContract {
    sealed class ViewState {
        data class Content(val feedItems: List<ViewEntity>) : ViewState()

        object EmptyState : ViewState()

        object Loading : ViewState()

        data class Error(val throwable: Throwable) : ViewState()
    }

    data class ViewEntity(val query: String, val results: List<ImageResultViewEntity> = emptyList())
}

class MainViewModel(
    private val getSearchResultViewEntities: GetSearchResultViewEntities,
    private val recordSearchHistory: RecordSearchHistory
) {
    val searchTerm = mutableStateOf("")
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val mutableViewState =
        MutableStateFlow<FeedScreenContract.ViewState>(FeedScreenContract.ViewState.Content(
            emptyList()))
    val viewState = mutableViewState
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing


    private val _shouldFilterFavourites = MutableStateFlow(false)
    val shouldFilterFavourites: StateFlow<Boolean> = _shouldFilterFavourites


//    private val _events = MutableSharedFlow<Event>()
//    val events: SharedFlow<Event> = _events

    fun search() = search(searchTerm.value)

    private fun search(query: String) {
        if (query.isNotEmpty()) {
            ioScope.launch(ioScope.coroutineContext) {
                recordSearchHistory(query)
                runCatching { getSearchResultViewEntities(query) }
                    .map { FeedScreenContract.ViewEntity(query, it) }
                    .onSuccess {
                        mutableViewState.value = FeedScreenContract.ViewState.Content(listOf(it))
//                        Log.e("SVM", "Testing success $it")
                    }
                    .onFailure {
                        mutableViewState.value = FeedScreenContract.ViewState.Error(it)
//                        Log.e("SVM", "Testing failed $it")
                    }
            }
        }
    }

    fun getSavedImages() {
        val queryHistoryItems = recordSearchHistory.getHistory()
        ioScope.launch(ioScope.coroutineContext) {
            val viewEntities = queryHistoryItems.toViewEntities()
            viewState.value = if (viewEntities.isEmpty()) {
                FeedScreenContract.ViewState.EmptyState
            } else {
                FeedScreenContract.ViewState.Content(viewEntities)
            }
        }
    }

    private suspend fun Set<String>.toViewEntities(): List<FeedScreenContract.ViewEntity> {
        return this.map { query ->
            val results = getSearchResultViewEntities(query)
            FeedScreenContract.ViewEntity(query, results)
        }
    }

    fun onToggleFavouriteFilter() {
        _shouldFilterFavourites.value = !shouldFilterFavourites.value
        if(shouldFilterFavourites.value) {
            val queryHistoryItems = recordSearchHistory.getHistory()
            ioScope.launch(ioScope.coroutineContext) {
                val viewEntities = queryHistoryItems.toViewEntities()
                viewState.value = if(viewEntities.isEmpty()) {
                    FeedScreenContract.ViewState.EmptyState
                } else {
                    FeedScreenContract.ViewState.Content(viewEntities)
                }
            }
        } else {
            search()
        }
    }

    fun isSearchButtonEnabled(): Boolean = searchTerm.value.isNotEmpty()

}
