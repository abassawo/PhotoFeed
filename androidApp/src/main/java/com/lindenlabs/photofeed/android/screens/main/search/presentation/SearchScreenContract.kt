package com.lindenlabs.photofeed.android.screens.main.search.presentation

import com.lindenlabs.photofeed.android.screens.main.search.presentation.entities.ImageResultViewEntity

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