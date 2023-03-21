package com.lindenlabs.photofeed.android.screens.feed.presentation

import androidx.lifecycle.ViewModel
import com.lindenlabs.photofeed.android.data.AppDataSource
import com.lindenlabs.photofeed.android.screens.feed.FeedScreenContract
import com.lindenlabs.photofeed.android.screens.search.presentation.SearchScreenContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(val appDataSource: AppDataSource) : ViewModel() {
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val mutableViewState = MutableStateFlow<FeedScreenContract.ViewState>(FeedScreenContract.ViewState.Loading)
    val viewState = mutableViewState

}
