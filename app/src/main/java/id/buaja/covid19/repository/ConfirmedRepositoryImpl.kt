package id.buaja.covid19.repository

import id.buaja.covid19.network.ApiService
import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.network.model.ResponseConfirmed
import retrofit2.Response

class ConfirmedRepositoryImpl(private val apiService: ApiService) : ConfirmedRepository {
    override suspend fun getConfirmed(): Response<List<ResponseConfirmed>> {
        return apiService.getConfirmed()
    }

    override suspend fun getProvinsi(): Response<List<ProvinsiResponse>> {
        return apiService.getProvinsi()
    }
}