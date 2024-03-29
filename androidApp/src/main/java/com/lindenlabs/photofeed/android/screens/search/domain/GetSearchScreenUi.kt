package com.lindenlabs.photofeed.android.screens.search.domain

import com.lindenlabs.photofeed.android.utils.ImageMemo
import com.lindenlabs.shared.data.AppDataSource
import javax.inject.Inject

interface SearchScreenInteractor {
    suspend operator fun invoke(query: String): List<DomainPhotoItem>
}

class GetSearchScreenUi @Inject constructor(private val appDataSource: AppDataSource) :
    SearchScreenInteractor {

    override suspend fun invoke(query: String): List<DomainPhotoItem> {
        return appDataSource.getImages(query)
            .map { DomainPhotoItem(it) }.also {
                for(image in it) {
                    ImageMemo.storeImage(image)
                }
            }
    }
}