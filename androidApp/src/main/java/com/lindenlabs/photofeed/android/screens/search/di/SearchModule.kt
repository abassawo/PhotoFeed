package com.lindenlabs.photofeed.android.screens.search.di

import com.lindenlabs.photofeed.android.data.AppDataSource
import com.lindenlabs.photofeed.android.screens.search.domain.GetSearchScreenUi
import com.lindenlabs.photofeed.android.screens.search.domain.SearchScreenInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class SearchModule {

    @Provides
    fun provideSearchInteractor(appDataSource: AppDataSource): SearchScreenInteractor = GetSearchScreenUi(appDataSource)
}