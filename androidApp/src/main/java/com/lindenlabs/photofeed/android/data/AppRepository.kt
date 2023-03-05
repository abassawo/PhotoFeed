package com.lindenlabs.photofeed.android.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

interface AppDataSource {
    suspend fun getImages(query: String): List<RawPhotoItem>
}

class AppRepository @Inject constructor(private val imageService: ImageService) : AppDataSource {
    override suspend fun getImages(query: String): List<RawPhotoItem> {
        val results = imageService.search(text = query).photos
        Log.d("SVM", "Results $results")
        return results.photo
    }
}

/**
 * Just for testing
 */
class LocalDataSource : AppDataSource {
    override suspend fun getImages(query: String): List<RawPhotoItem> {
        return listOf(
            RawPhotoItem(
                id = "52717402473",
                server = "65535",
                secret = "4c8a4f8ab4",
                title = "Some People With Severe Depression - People With Mental Illnesses Who Enrich Or Have Enriched Our Lives"
            )
        )
    }
}