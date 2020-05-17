package id.buaja.covid19.data.repository.timeline

import com.google.gson.JsonObject
import retrofit2.Response

/**
 * Created By Julsapargi Nursam 3/29/20
 */

interface TimeLineRepository {
    suspend fun getTimeLine(): Response<JsonObject>
}