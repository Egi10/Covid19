package id.buaja.covid19.data.source.response.province


import com.google.gson.annotations.SerializedName

data class ProvinceCovidResponse(
    @SerializedName("current_data")
    val currentData: Double,
    @SerializedName("last_date")
    val lastDate: String,
    @SerializedName("list_data")
    val listData: List<Data>,
    @SerializedName("missing_data")
    val missingData: Double,
    @SerializedName("tanpa_provinsi")
    val tanpaProvinsi: Int
)