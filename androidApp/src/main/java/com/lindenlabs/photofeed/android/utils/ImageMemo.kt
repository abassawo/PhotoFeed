package com.lindenlabs.photofeed.android.utils

import com.lindenlabs.photofeed.android.screens.search.domain.DomainPhotoItem
import java.util.concurrent.ConcurrentHashMap

object ImageMemo {
    val map = ConcurrentHashMap<String, DomainPhotoItem>()

    fun storeImage(domainPhotoItem: DomainPhotoItem) {
        map.put(domainPhotoItem.rawPhotoItem.id, domainPhotoItem)
    }

    fun getImage(id: String): DomainPhotoItem? = map[id]
}