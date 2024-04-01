package com.lindenlabs.photofeed

import com.lindenlabs.photofeed.android.screens.search.domain.GetSearchScreenUi
import com.lindenlabs.photofeed.android.screens.search.domain.RecordSearchHistory
import com.lindenlabs.photofeed.screens.saved.FeedViewModel
import com.lindenlabs.photofeed.screens.search.SearchViewModel
import com.lindenlabs.shared.data.AppRepository
import domain.GetSearchResultViewEntities
import domain.SearchViewMapper
import domain.makeSharedPreferences
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
//    single { RecordSearchHistory(makeSharedPreferences()) }
//    single { SearchViewMapper() }
//    single { AppRepository() }
//    single { GetSearchScreenUi(get()) }
//    single { GetSearchResultViewEntities(get(), get()) }
    viewModel {
        FeedViewModel(
        GetSearchResultViewEntities(
            SearchViewMapper(),
            GetSearchScreenUi(AppRepository())
        ), RecordSearchHistory(makeSharedPreferences()))
    }
    viewModel {
        SearchViewModel(
            GetSearchResultViewEntities(
                SearchViewMapper(),
                GetSearchScreenUi(AppRepository())
            ), RecordSearchHistory(makeSharedPreferences())
        )
    }
}