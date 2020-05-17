package id.buaja.covid19.usecase.news

import id.buaja.covid19.repository.news.NewsRepository
import id.buaja.covid19.util.network.ResultState
import id.buaja.covid19.util.network.fetchState
import id.buaja.covid19.util.network.wrapper

/**
 * Created By Julsapargi Nursam 3/28/20
 */

class NewsUseCase(private val repository: NewsRepository) {
    suspend fun getNews(): ResultState<Any> {
        return fetchState {
            wrapper {
                repository.getNews()
            }
        }
    }
}