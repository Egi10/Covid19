package id.buaja.covid19.repository.confirmed

import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.network.model.ResponseConfirmed
import retrofit2.Response

interface ConfirmedRepository {
    suspend fun getConfirmed(): Response<List<ResponseConfirmed>>
    suspend fun getProvinsi(): Response<List<ProvinsiResponse>>
}