package utils

import domain.DomainPhotoItem

object ImageMemo {
    val map = HashMap<String, DomainPhotoItem>()

    fun storeImage(domainPhotoItem: DomainPhotoItem) {
        map.put(domainPhotoItem.rawPhotoItem.id, domainPhotoItem)
    }

    fun getImage(id: String): DomainPhotoItem? = map[id]
}