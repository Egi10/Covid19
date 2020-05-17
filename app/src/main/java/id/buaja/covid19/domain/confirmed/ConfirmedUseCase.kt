package id.buaja.covid19.domain.confirmed

import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.data.repository.confirmed.ConfirmedRepository
import id.buaja.covid19.util.ResultState
import id.buaja.covid19.util.fetchError
import id.buaja.covid19.util.fetchState


class ConfirmedUseCase(private val repository: ConfirmedRepository) {
    suspend fun getConfirmed(): ResultState<Any> {
        return fetchState {
            val response = repository.getConfirmed()

            if (response.isSuccessful) {
                ResultState.Success(response.body())
            } else {
                fetchError(response)
            }
        }
    }

    suspend fun getProvince(): ResultState<List<ProvinsiResponse>> {
        return fetchState {
            val response = repository.getProvinsi()

            val code = response.code()
            when {
                code == 200 -> {
                    ResultState.Success(response.body())
                }

                code >= 500 -> {
                    ResultState.Error("Server Kami Sedang Bermasalah")
                }

                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }
}