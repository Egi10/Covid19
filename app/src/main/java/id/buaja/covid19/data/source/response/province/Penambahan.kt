package id.buaja.covid19.data.source.response.province


import com.google.gson.annotations.SerializedName

data class Penambahan(
    @SerializedName("meninggal")
    val meninggal: Int,
    @SerializedName("positif")
    val positif: Int,
    @SerializedName("sembuh")
    val sembuh: Int
)