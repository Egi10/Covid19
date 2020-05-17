package id.buaja.covid19.data.repository.news

import id.buaja.covid19.util.ResultState

/**
 * Created By Julsapargi Nursam 3/28/20
 */

interface NewsRepository {
    suspend fun getNews(): ResultState<Any>
}