package id.buaja.covid19.usecase.confirmed

import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.network.model.ResponseConfirmed
import id.buaja.covid19.repository.confirmed.ConfirmedRepository
import id.buaja.covid19.util.ResultState
import id.buaja.covid19.util.fetchState


class ConfirmedUseCase(private val repository: ConfirmedRepository) {
    suspend fun getConfirmed(): ResultState<List<ResponseConfirmed>> {
        return fetchState {
            val response = repository.getConfirmed()

            when(response.code()) {
                200 -> {
                    ResultState.Success(response.body())
                }

                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }

    suspend fun getProvince(): ResultState<List<ProvinsiResponse>> {
        return fetchState {
            val response = repository.getProvinsi()

            when(response.code()) {
                200 -> {
                    ResultState.Success(response.body())
                }

                503 -> {
                    ResultState.Error("Server Kami Sedang Bermasalah")
                }

                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }
}