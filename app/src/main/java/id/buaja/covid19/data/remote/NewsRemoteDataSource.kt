package id.buaja.covid19.data.remote

import id.buaja.covid19.BuildConfig
import id.buaja.covid19.data.source.NewsDataSource
import id.buaja.covid19.network.ApiService
import id.buaja.covid19.util.ResultState
import id.buaja.covid19.util.fetchError
import id.buaja.covid19.util.fetchState

/**
 * Created By Julsapargi Nursam 5/17/20
 */
 
class NewsRemoteDataSource(private val apiService: ApiService): NewsDataSource {
    override suspend fun getNews(): ResultState<Any> {
        return fetchState {
            val response = apiService.getNews("corona", BuildConfig.API_KEY, "id")

            if (response.isSuccessful) {
                ResultState.Success(response.body()?.articles)
            } else {
                fetchError(response)
            }
        }
    }

}