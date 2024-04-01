package data

interface AppDataSource {
    suspend fun getImages(query: String): List<RawPhotoItem>
}