package id.buaja.covid19.data.source.response.province


import com.google.gson.annotations.SerializedName

data class JenisKelamin(
    @SerializedName("doc_count")
    val docCount: Int,
    @SerializedName("key")
    val key: String
)