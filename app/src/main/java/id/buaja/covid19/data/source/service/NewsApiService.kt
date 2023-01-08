package id.buaja.covid19.data.source.service

import id.buaja.covid19.data.source.response.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?
    ): NewsResponse
}