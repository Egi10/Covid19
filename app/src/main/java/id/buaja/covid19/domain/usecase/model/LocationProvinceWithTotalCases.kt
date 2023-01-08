package id.buaja.covid19.domain.usecase.model

data class LocationProvinceWithTotalCases(
    val totalConfirmed: Int,
    val totalRecovered: Int,
    val totalDeaths: Int,
    val lastUpdate: String,
    val locationProvince: List<LocationProvince>
)

data class LocationProvince(
    val lat: Double,
    val long: Double,
    val provinceName: String
)
