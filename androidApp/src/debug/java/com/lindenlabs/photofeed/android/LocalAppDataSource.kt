package com.lindenlabs.photofeed.android

import com.lindenlabs.photofeed.android.data.AppDataSource
import com.lindenlabs.photofeed.android.data.RawPhotoItem

/**
 * Just for testing
 */
class LocalAppDataSource : AppDataSource {
    override suspend fun getImages(query: String): List<RawPhotoItem> {
        val imageTemplate = RawPhotoItem(
            id = "52717402473",
            server = "65535",
            secret = "4c8a4f8ab4",
            title = "Some People With Severe Depression - People With Mental Illnesses Who Enrich Or Have Enriched Our Lives"
        )
        val testImages = mutableListOf<RawPhotoItem>()
        repeat(25) {
            testImages.add(imageTemplate)
        }

        return testImages
    }
}