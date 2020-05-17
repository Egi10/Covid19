package id.buaja.covid19.domain.news

import id.buaja.covid19.data.repository.news.NewsRepository
import id.buaja.covid19.util.ResultState
import id.buaja.covid19.util.fetchError
import id.buaja.covid19.util.fetchState

/**
 * Created By Julsapargi Nursam 3/28/20
 */

class NewsUseCase(private val repository: NewsRepository) {
    suspend fun getNews(): ResultState<Any> {
        return repository.getNews()
    }
}