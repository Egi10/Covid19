package id.buaja.covid19.domain.repository

import id.buaja.covid19.domain.usecase.model.LocationProvinceWithTotalCases
import id.buaja.covid19.domain.usecase.model.ProvinceCovid
import kotlinx.coroutines.flow.Flow

interface CovidRepository {
    fun getProvinceCovid(): Flow<ProvinceCovid>
    fun getLocationProvinceWithTotalCases(): Flow<LocationProvinceWithTotalCases>
}