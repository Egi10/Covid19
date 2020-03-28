package id.buaja.covid19.repository.news

import id.buaja.covid19.network.model.news.NewsResponse
import retrofit2.Response

/**
 * Created By Julsapargi Nursam 3/28/20
 */

interface NewsRepository {
    suspend fun getNews(): Response<NewsResponse>
}