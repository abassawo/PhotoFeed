package com.lindenlabs.photofeed.android.di

import com.lindenlabs.photofeed.android.LocalAppDataSource
import com.lindenlabs.photofeed.android.data.AppDataSource
import com.lindenlabs.photofeed.android.data.AppRepository
import com.lindenlabs.photofeed.android.data.ImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {

        val client = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(ImageService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAppDataSource(retrofit: Retrofit): AppDataSource =
        AppRepository(retrofit.create())

}