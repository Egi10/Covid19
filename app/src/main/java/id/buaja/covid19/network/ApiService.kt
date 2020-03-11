package id.buaja.covid19.network

import id.buaja.covid19.network.model.ResponseConfirmed
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("confirmed")
    suspend fun getConfirmed(): Response<List<ResponseConfirmed>>
}