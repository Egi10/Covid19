package id.buaja.covid19.data.source

import id.buaja.covid19.data.source.response.province.ProvinceCovidResponse

interface RemoteDataSource {
    suspend fun getProvinceCovid(): ProvinceCovidResponse
}