package com.lindenlabs.photofeed.android.screens.search.domain

import data.AppDataSource
import domain.DomainPhotoItem
import utils.ImageMemo

interface SearchScreenInteractor {
    suspend operator fun invoke(query: String): List<DomainPhotoItem>
}

class GetSearchScreenUi constructor(private val appDataSource: AppDataSource) :
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