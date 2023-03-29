package com.lindenlabs.photofeed.android.di

import com.lindenlabs.shared.data.AppDataSource
import com.lindenlabs.shared.data.AppRepository
import com.lindenlabs.shared.data.ImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideAppDataSource(): AppDataSource =
        AppRepository()

}