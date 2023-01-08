package id.buaja.covid19.data.source

import id.buaja.covid19.data.source.response.province.ProvinceCovidResponse
import id.buaja.covid19.data.source.service.CovidApiService

class RemoteDataSourceImpl(
    private val covidApiService: CovidApiService
): RemoteDataSource {
    override suspend fun getProvinceCovid(): ProvinceCovidResponse {
        return covidApiService.getProvinceCovid()
    }
}