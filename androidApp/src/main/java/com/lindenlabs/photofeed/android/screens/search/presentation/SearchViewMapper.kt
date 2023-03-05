package com.lindenlabs.photofeed.android.screens.search.presentation

import com.lindenlabs.photofeed.android.screens.search.domain.DomainPhotoItem
import com.lindenlabs.photofeed.android.screens.search.presentation.entities.ImageResultViewEntity
import javax.inject.Inject

class SearchViewMapper @Inject constructor(){

    fun map(domainEntities: List<DomainPhotoItem>): List<ImageResultViewEntity> =
            domainEntities.map { ImageResultViewEntity(it) }
}