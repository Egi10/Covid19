package id.buaja.covid19.usecase.news

import id.buaja.covid19.repository.news.NewsRepository
import id.buaja.covid19.util.ResultState
import id.buaja.covid19.util.fetchError
import id.buaja.covid19.util.fetchState

/**
 * Created By Julsapargi Nursam 3/28/20
 */

class NewsUseCase(private val repository: NewsRepository) {
    suspend fun getNews(): ResultState<Any> {
        return fetchState {
            val response = repository.getNews()

            when(response.code()) {
                200 -> {
                    ResultState.Success(response.body()?.articles)
                }

                else -> {
                    fetchError(response)
                }
            }
        }
    }
}