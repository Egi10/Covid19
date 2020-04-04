package id.buaja.covid19.network

import com.google.gson.JsonObject
import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.network.model.ResponseConfirmed
import id.buaja.covid19.network.model.news.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("countries/Indonesia/confirmd")
    suspend fun getConfirmed(): Response<List<ResponseConfirmed>>

    @GET("https://api.kawalcorona.com/indonesia/provinsi")
    suspend fun getProvinsi(): Response<List<ProvinsiResponse>>

    @GET("https://newsapi.org/v2/top-headlines")
    suspend fun getNews(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("country") country: String?
    ): Response<NewsResponse>

    @GET("https://thevirustracker.com/free-api")
    suspend fun getTimeLine(
        @Query("countryTimeline") country: String?
    ): Response<JsonObject>
}