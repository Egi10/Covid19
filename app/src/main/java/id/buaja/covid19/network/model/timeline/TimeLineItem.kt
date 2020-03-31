package id.buaja.covid19.network.model.timeline

import com.google.gson.annotations.SerializedName

data class TimeLineItem(
	val date: String? = null,

	@field:SerializedName("total_cases")
	val totalCases: Int? = null,

	@field:SerializedName("total_recoveries")
	val totalRecoveries: Int? = null,

	@field:SerializedName("new_daily_deaths")
	val newDailyDeaths: Int? = null,

	@field:SerializedName("total_deaths")
	val totalDeaths: Int? = null,

	@field:SerializedName("new_daily_cases")
	val newDailyCases: Int? = null
)