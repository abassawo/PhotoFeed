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
internal class SearchViewModel @Inject constructor(
    val getSearchScreenUi: SearchScreenInteractor,
    private val viewMapper: SearchViewMapper
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
                    .onSuccess {
                        Log.e("SVM", "Testing success $it")
                        viewState.value = ViewState.Initial(query, results = it, true) }
                    .onFailure {
                        Log.e("SVM", "Testing failed $it")
                    }
            }
        }
    }

    fun updateQuery(query: String) {
        viewState.value = (viewState.value as ViewState.Initial).copy(query = query)
    }
}
