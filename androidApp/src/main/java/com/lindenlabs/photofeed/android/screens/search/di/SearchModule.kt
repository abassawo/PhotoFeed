package com.lindenlabs.photofeed.android.screens.search.di

import android.content.Context
import android.content.SharedPreferences
import com.lindenlabs.shared.data.AppDataSource
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
    fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences("history", Context.MODE_PRIVATE)

    @Provides
    fun provideSearchInteractor(appDataSource: AppDataSource): SearchScreenInteractor = GetSearchScreenUi(appDataSource)
}