package com.lindenlabs.photofeed.android.screens.search.presentation

import com.lindenlabs.photofeed.android.screens.search.domain.SearchScreenInteractor
import com.lindenlabs.photofeed.android.screens.search.presentation.entities.ImageResultViewEntity
import javax.inject.Inject

internal class GetSearchResultViewEntities @Inject constructor(
    private val viewMapper: SearchViewMapper,
    private val getSearchScreenUi: SearchScreenInteractor
) {
    suspend operator fun invoke(query: String): List<ImageResultViewEntity> =
        viewMapper.map(getSearchScreenUi(query))

}