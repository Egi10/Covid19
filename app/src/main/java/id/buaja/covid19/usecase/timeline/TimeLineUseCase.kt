package id.buaja.covid19.usecase.timeline

import com.google.gson.JsonObject
import id.buaja.covid19.network.model.timeline.TimeLineItem
import id.buaja.covid19.repository.timeline.TimeLineRepository
import id.buaja.covid19.util.ResultState
import id.buaja.covid19.util.fetchState

/**
 * Created By Julsapargi Nursam 3/29/20
 */

class TimeLineUseCase(private val repository: TimeLineRepository) {
    private val list: MutableList<TimeLineItem> = mutableListOf()
    suspend fun getTimeLine(): ResultState<List<TimeLineItem>> {
        return fetchState {
            val response = repository.getTimeLine()

            when (response.code()) {
                200 -> {
                    val responseTimeLine = response.body()?.getAsJsonArray("timelineitems")
                    responseTimeLine?.let {
                        val getObjectResponse = it[0].asJsonObject
                        val listInResponse = getObjectResponse.keySet().toList()

                        val filter = listInResponse.filter { response ->
                            response != "stat"
                        }

                        for (i in filter.indices) {
                            val responseObject =
                                getObjectResponse.get(filter[i].toString()).asJsonObject
                            responseObject?.apply {
                                val newDailyCases = get("new_daily_cases").asInt
                                val newDailyDeaths = get("new_daily_deaths").asInt
                                val totalCases = get("total_cases").asInt
                                val totalRecoveries = get("total_recoveries").asInt
                                val totalDeaths = get("total_deaths").asInt
                                val timeLineItem = TimeLineItem(
                                    date = filter[i].toString(),
                                    totalCases = totalCases,
                                    newDailyCases = newDailyCases,
                                    newDailyDeaths = newDailyDeaths,
                                    totalRecoveries = totalRecoveries,
                                    totalDeaths = totalDeaths
                                )
                                list.add(timeLineItem)
                            }
                        }
                    }
                    ResultState.Success(list)
                }

                else -> {
                    ResultState.Error(response.message())
                }
            }
        }
    }
}