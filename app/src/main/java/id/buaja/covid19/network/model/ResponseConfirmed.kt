package id.buaja.covid19.network.model

import com.google.gson.annotations.SerializedName

data class ResponseConfirmed(

	@field:SerializedName("recovered")
	val recovered: Int? = null,

	@field:SerializedName("countryRegion")
	val countryRegion: String? = null,

	@field:SerializedName("lastUpdate")
	val lastUpdate: Long? = null,

	@field:SerializedName("confirmed")
	val confirmed: Int? = null,

	@field:SerializedName("provinceState")
	val provinceState: String? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,

	@field:SerializedName("long")
	val jsonMemberLong: Double? = null,

	@field:SerializedName("deaths")
	val deaths: Int? = null
)