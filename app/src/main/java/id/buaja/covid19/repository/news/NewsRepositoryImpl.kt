package id.buaja.covid19.repository.news

import id.buaja.covid19.BuildConfig
import id.buaja.covid19.network.ApiService
import id.buaja.covid19.network.model.news.NewsResponse
import retrofit2.Response

/**
 * Created By Julsapargi Nursam 3/28/20
 */

class NewsRepositoryImpl(private val apiService: ApiService) : NewsRepository {
    override suspend fun getNews(): Response<NewsResponse> {
        return apiService.getNews("corona", BuildConfig.API_KEY, "id")
    }
}