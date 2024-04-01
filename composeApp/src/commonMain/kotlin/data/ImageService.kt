package data


annotation class Query(val value: String)
interface ImageService{
    companion object {
        const val BASE_URL = "https://www.flickr.com/services/rest"
        const val API_KEY = "1508443e49213ff84d566777dc211f2a"
    }
}