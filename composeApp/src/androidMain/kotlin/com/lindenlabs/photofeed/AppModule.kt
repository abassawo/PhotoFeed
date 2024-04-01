package com.lindenlabs.photofeed

import com.lindenlabs.photofeed.android.screens.search.domain.GetSearchScreenUi
import domain.RecordSearchHistory
import com.lindenlabs.photofeed.screens.saved.FeedViewModel
import com.lindenlabs.photofeed.screens.search.SearchViewModel
import com.lindenlabs.shared.data.AppRepository
import domain.GetSearchResultViewEntities
import domain.SearchViewMapper
import domain.makeSharedPreferences
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    single { makeSharedPreferences() }
    single { RecordSearchHistory(get()) }
    single { GetSearchResultViewEntities(
        SearchViewMapper(),
        GetSearchScreenUi(AppRepository())
    ) }
    viewModel {
        FeedViewModel(
            get(), get()
        )
    }
    viewModel {
        SearchViewModel(
            get(), get()
        )
    }
}