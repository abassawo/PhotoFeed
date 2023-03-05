package com.lindenlabs.photofeed.android.data

data class RawResponseEntity(val photos: Photos)

data class Photos(
    val page: Int,
    val pages: Int,
    val photo: List<RawPhotoItem>
)

data class RawPhotoItem(
    val id: String,
    val server: String,
    val secret: String,
    val title: String)