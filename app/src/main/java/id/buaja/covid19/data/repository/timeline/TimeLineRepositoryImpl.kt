package id.buaja.covid19.data.repository.timeline

import com.google.gson.JsonObject
import id.buaja.covid19.network.ApiService
import retrofit2.Response

/**
 * Created By Julsapargi Nursam 3/29/20
 */

class TimeLineRepositoryImpl(private val apiService: ApiService): TimeLineRepository {
    override suspend fun getTimeLine(): Response<JsonObject> {
        return apiService.getTimeLine("ID")
    }
}