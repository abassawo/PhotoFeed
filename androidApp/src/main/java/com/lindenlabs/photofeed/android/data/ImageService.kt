package com.lindenlabs.photofeed.android.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService{
    companion object {
        const val BASE_URL = "https://www.flickr.com/services/"
        const val API_KEY = "1508443e49213ff84d566777dc211f2a"
    }

    @GET("rest/")
    suspend fun search(
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") api_key: String = "1508443e49213ff84d566777dc211f2a",
        @Query("format") format: String = "json",
        @Query("perpages") perpages: Int = 100,
        @Query("nojsoncallback") nojsoncallback: String = "1",
        @Query("text") text: String
    ): RawResponseEntity
}