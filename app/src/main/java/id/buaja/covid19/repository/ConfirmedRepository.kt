package id.buaja.covid19.repository

import id.buaja.covid19.network.model.ResponseConfirmed
import retrofit2.Response

interface ConfirmedRepository {
    suspend fun getConfirmed(): Response<List<ResponseConfirmed>>
}