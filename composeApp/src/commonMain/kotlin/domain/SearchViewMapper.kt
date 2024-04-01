package domain

import presentation.ImageResultViewEntity

class SearchViewMapper{

    fun map(domainEntities: List<DomainPhotoItem>): List<ImageResultViewEntity> =
        domainEntities.map { ImageResultViewEntity(it) }
}

