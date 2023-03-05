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
        val body = """
           {
           	"photos": {
           		"page": 1,
           		"pages": 168,
           		"perpage": 100,
           		"total": 16798,
           		"photo": [{
           			"id": "52717402473",
           			"owner": "80454089@N00",
           			"secret": "4c8a4f8ab4",
           			"server": "65535",
           			"farm": 66,
           			"title": "Some People With Severe Depression - People With Mental Illnesses Who Enrich Or Have Enriched Our Lives",
           			"ispublic": 1,
           			"isfriend": 0,
           			"isfamily": 0
           		}]
           	}
           }
       """.trimIndent()
        val listType = object : TypeToken<ArrayList<RawPhotoItem?>?>() {}.getType()

        return Gson().fromJson(body, listType)
    }
}