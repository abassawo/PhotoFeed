package com.lindenlabs.photofeed.android.di

import com.lindenlabs.photofeed.android.data.AppDataSource
import com.lindenlabs.photofeed.android.data.AppRepository
import com.lindenlabs.photofeed.android.data.ImageService
import com.lindenlabs.photofeed.android.data.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC;

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(ImageService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(Converter
            .build()
    }

    @Provides
    fun provideAppDataSource(retrofit: Retrofit): AppDataSource =
        LocalDataSource()
//        AppRepository(retrofit.create())

}