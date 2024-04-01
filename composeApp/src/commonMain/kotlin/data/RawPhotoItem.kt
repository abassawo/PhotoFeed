package data

@kotlinx.serialization.Serializable
data class RawResponseEntity(val photos: Photos)

@kotlinx.serialization.Serializable
data class Photos(
    val page: Int,
    val pages: Int,
    val photo: List<RawPhotoItem>
)

@kotlinx.serialization.Serializable
data class RawPhotoItem(
    val id: String,
    val server: String,
    val secret: String,
    val title: String)