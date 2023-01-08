package id.buaja.covid19.data.source.response.province


import com.google.gson.annotations.SerializedName

data class Lokasi(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)