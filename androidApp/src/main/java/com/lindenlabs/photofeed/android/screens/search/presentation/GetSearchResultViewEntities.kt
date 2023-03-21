package com.lindenlabs.photofeed.android.screens.search.presentation

import com.lindenlabs.photofeed.android.screens.search.domain.SearchScreenInteractor
import javax.inject.Inject

internal class GetSearchResultViewEntities @Inject constructor(
    private val viewMapper: SearchViewMapper,
    private val getSearchScreenUi: SearchScreenInteractor
) {
    suspend operator fun invoke(query: String) =
        viewMapper.map(getSearchScreenUi(query))

}