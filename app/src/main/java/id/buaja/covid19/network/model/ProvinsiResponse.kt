package id.buaja.covid19.network.model

import com.google.gson.annotations.SerializedName

data class ProvinsiResponse(

	@field:SerializedName("attributes")
	val attributes: Attributes? = null
)