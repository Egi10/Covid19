package id.buaja.covid19.domain.usecase

import id.buaja.covid19.domain.repository.CovidRepository
import id.buaja.covid19.domain.usecase.model.ProvinceCovid
import id.buaja.covid19.util.network.ResultState
import id.buaja.covid19.util.network.asResult
import kotlinx.coroutines.flow.Flow

class GetProvinceCovidUseCase(
    private val confirmedRepository: CovidRepository
) {
    operator fun invoke(): Flow<ResultState<ProvinceCovid>> {
        return confirmedRepository.getProvinceCovid().asResult()
    }
}