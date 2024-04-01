package com.lindenlabs.shared.data

import data.AppDataSource
import data.ImageService
import data.RawPhotoItem
import data.RawResponseEntity
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class AppRepository : AppDataSource {

    private val client: HttpClient =
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
        }

    override suspend fun getImages(query: String): List<RawPhotoItem> =
        search(query).photos.photo

    private suspend fun search(text: String): RawResponseEntity {
        val query = URLBuilder(ImageService.BASE_URL).apply {
            parameters.append("method", "flickr.photos.search")
            parameters.append("api_key", ImageService.API_KEY)
            parameters.append("format", "json")
            parameters.append("perpages", "100")
            parameters.append("nojsoncallback", "1")
            parameters.append("text", text)
        }.buildString()
        return client.get(query).body()
    }
}