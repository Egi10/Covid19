package id.buaja.covid19.network

import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.network.model.ResponseConfirmed
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("countries/Indonesia/confirmed")
    suspend fun getConfirmed(): Response<List<ResponseConfirmed>>

    @GET("https://api.kawalcorona.com/indonesia/provinsi")
    suspend fun getProvinsi(): Response<List<ProvinsiResponse>>
}