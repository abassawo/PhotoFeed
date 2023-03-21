package com.lindenlabs.photofeed.android.screens.feed

import com.lindenlabs.photofeed.android.screens.search.presentation.entities.ImageResultViewEntity

object FeedScreenContract {
    sealed class ViewState {
        data class Content(val feedItems: List<ViewEntity>) : ViewState()

        object EmptyState : ViewState()

        object Loading : ViewState()

        data class Error(val throwable: Throwable) : ViewState()
    }

    data class ViewEntity(val query: String, val results: List<ImageResultViewEntity> = emptyList())
}