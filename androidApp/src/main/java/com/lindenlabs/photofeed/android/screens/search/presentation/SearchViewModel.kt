package com.lindenlabs.photofeed.android.screens.search.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SearchViewModel @Inject constructor(
    val getSearchScreenUi: SearchScreenInteractor,
    val viewMapper: SearchViewMapper
) : ViewModel() {
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    val viewState =
        MutableStateFlow<ViewState>(ViewState.Initial(""))

    fun search(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch(ioScope.coroutineContext) {
                runCatching { getSearchScreenUi(query) }
                    .mapCatching { viewMapper.map(it) }
                    .onSuccess { viewState.value = ViewState.Initial(query, it, true) }
                    .onFailure {
                        Log.e("SVM", "Testing failed $it")
                    }
            }
        }
    }

    fun onToggleSearch() {
        val initialState =
            (viewState.value as? ViewState.Initial) ?: ViewState.Initial()
        viewState.value = initialState.copy(isSearchActive = initialState.isSearchActive.not())
    }

    fun updateQuery(query: String) {
        viewState.value = (viewState.value as ViewState.Initial).copy(query = query)
    }
}
