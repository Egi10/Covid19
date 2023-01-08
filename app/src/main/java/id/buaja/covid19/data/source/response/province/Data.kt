package id.buaja.covid19.data.source.response.province


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("doc_count")
    val docCount: Double,
    @SerializedName("jenis_kelamin")
    val jenisKelamin: List<JenisKelamin>,
    @SerializedName("jumlah_dirawat")
    val jumlahDirawat: Int,
    @SerializedName("jumlah_kasus")
    val jumlahKasus: Int,
    @SerializedName("jumlah_meninggal")
    val jumlahMeninggal: Int,
    @SerializedName("jumlah_sembuh")
    val jumlahSembuh: Int,
    @SerializedName("kelompok_umur")
    val kelompokUmur: List<KelompokUmur>,
    @SerializedName("key")
    val key: String,
    @SerializedName("lokasi")
    val lokasi: Lokasi,
    @SerializedName("penambahan")
    val penambahan: Penambahan
)