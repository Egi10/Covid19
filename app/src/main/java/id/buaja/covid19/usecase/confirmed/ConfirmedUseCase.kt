package id.buaja.covid19.usecase.confirmed

import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.network.model.ResponseConfirmed
import id.buaja.covid19.repository.confirmed.ConfirmedRepository
import id.buaja.covid19.util.ResultState
import id.buaja.covid19.util.fetchState
import retrofit2.Response


class ConfirmedUseCase(private val repository: ConfirmedRepository) {
    suspend fun getConfirmed(): ResultState<Response<List<ResponseConfirmed>>> {
        return fetchState {
            repository.getConfirmed()
        }
    }

    suspend fun getProvince(): ResultState<List<ProvinsiResponse>> {
        return fetchState {
            repository.getProvinsi().body()!!
        }
    }
}