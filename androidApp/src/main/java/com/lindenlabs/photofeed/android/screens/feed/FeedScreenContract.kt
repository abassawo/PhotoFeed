package com.lindenlabs.photofeed.android.screens.feed

object FeedScreenContract {
    sealed class ViewState {
        data class Content(val feedItems: List<String>) : ViewState()

        object EmptyState : ViewState()

        object Loading : ViewState()

        data class Error(val throwable: Throwable) : ViewState()
    }
}