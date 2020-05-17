package id.buaja.covid19.usecase.confirmed

import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.repository.confirmed.ConfirmedRepository
import id.buaja.covid19.util.network.ResultState
import id.buaja.covid19.util.network.fetchState
import id.buaja.covid19.util.network.wrapper


class ConfirmedUseCase(private val repository: ConfirmedRepository) {
    suspend fun getConfirmed(): ResultState<Any> {
        return fetchState {
            wrapper {
                repository.getConfirmed()
            }
        }
    }

    suspend fun getProvince(): ResultState<List<ProvinsiResponse>> {
        return fetchState {
            wrapper {
                repository.getProvinsi()
            }
        }
    }
}