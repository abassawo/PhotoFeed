package com.lindenlabs.photofeed.android.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import kotlin.text.Typography.times

interface AppDataSource {
    suspend fun getImages(query: String): List<RawPhotoItem>
}

class AppRepository @Inject constructor(private val imageService: ImageService) : AppDataSource {
    override suspend fun getImages(query: String): List<RawPhotoItem> {
        val results = imageService.search(text = query).photos
        Log.d("SVM", "Results $results")
        return results.photo.take(27)
    }
}