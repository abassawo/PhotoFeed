package com.lindenlabs.photofeed.android.screens.main.search.presentation

import com.lindenlabs.photofeed.android.screens.main.search.domain.DomainPhotoItem
import com.lindenlabs.photofeed.android.screens.main.search.presentation.entities.ImageResultViewEntity
import javax.inject.Inject

class SearchViewMapper @Inject constructor(){

    fun map(domainEntities: List<DomainPhotoItem>): List<ImageResultViewEntity> =
            domainEntities.map { ImageResultViewEntity(it) }
}