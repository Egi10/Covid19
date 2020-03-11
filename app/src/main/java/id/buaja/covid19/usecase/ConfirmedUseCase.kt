package id.buaja.covid19.usecase

import id.buaja.covid19.network.model.ResponseConfirmed
import id.buaja.covid19.repository.ConfirmedRepository
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
}