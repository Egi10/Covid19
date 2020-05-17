package id.buaja.covid19.data.source

import id.buaja.covid19.util.ResultState

/**
 * Created By Julsapargi Nursam 5/17/20
 */

interface NewsDataSource {
    suspend fun getNews(): ResultState<Any>
}