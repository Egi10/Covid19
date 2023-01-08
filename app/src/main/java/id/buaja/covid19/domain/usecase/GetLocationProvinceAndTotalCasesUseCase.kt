package id.buaja.covid19.domain.usecase

import id.buaja.covid19.domain.repository.CovidRepository
import id.buaja.covid19.domain.usecase.model.LocationProvinceWithTotalCases
import id.buaja.covid19.util.network.ResultState
import id.buaja.covid19.util.network.asResult
import kotlinx.coroutines.flow.Flow

class GetLocationProvinceAndTotalCasesUseCase(
    private val covidRepository: CovidRepository
) {
    operator fun invoke(): Flow<ResultState<LocationProvinceWithTotalCases>> {
        return covidRepository.getLocationProvinceWithTotalCases().asResult()
    }
}