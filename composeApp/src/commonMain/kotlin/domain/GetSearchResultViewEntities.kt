package domain

import com.lindenlabs.photofeed.android.screens.search.domain.SearchScreenInteractor
import presentation.ImageResultViewEntity

class GetSearchResultViewEntities (
    private val viewMapper: SearchViewMapper,
    private val getSearchScreenUi: SearchScreenInteractor
) {
    suspend operator fun invoke(query: String): List<ImageResultViewEntity> =
        viewMapper.map(getSearchScreenUi(query))

}