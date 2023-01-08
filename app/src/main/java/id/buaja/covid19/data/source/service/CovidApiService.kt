package id.buaja.covid19.data.source.service

import id.buaja.covid19.data.source.response.province.ProvinceCovidResponse
import retrofit2.http.GET

interface CovidApiService {
    @GET("prov.json")
    suspend fun getProvinceCovid(): ProvinceCovidResponse
}