package id.buaja.covid19.data.repository.mapping

import id.buaja.covid19.data.source.response.news.ArticlesItem
import id.buaja.covid19.data.source.response.province.ProvinceCovidResponse
import id.buaja.covid19.domain.usecase.model.*

fun ProvinceCovidResponse.mapToEntityDomain(): ProvinceCovid {
    val newList: MutableList<ProvinceCases> = mutableListOf()

    this.listData.forEach {
        newList.add(
            ProvinceCases(
                name = it.key,
                totalPositiveCases = it.jumlahKasus,
                totalCasesRecovery = it.jumlahSembuh,
                totalDeathCases = it.jumlahMeninggal
            )
        )
    }

    return ProvinceCovid(
        lastUpdate = this.lastDate,
        provinceCases = newList
    )
}

fun ProvinceCovidResponse.mapToEntityLocationDomain(): LocationProvinceWithTotalCases {
    var totalConfirmed = 0
    var totalRecovery = 0
    var totalDeaths = 0
    val listLocation: MutableList<LocationProvince> = mutableListOf()

    this.listData.forEach {
        totalConfirmed += it.jumlahKasus
        totalRecovery += it.jumlahSembuh
        totalDeaths += it.jumlahMeninggal

        listLocation.add(
            LocationProvince(
                lat = it.lokasi.lat,
                long = it.lokasi.lon,
                provinceName = it.key
            )
        )
    }

    return LocationProvinceWithTotalCases(
        totalConfirmed = totalConfirmed,
        totalRecovered = totalRecovery,
        totalDeaths = totalDeaths,
        lastUpdate = this.lastDate,
        locationProvince = listLocation
    )
}

fun List<ArticlesItem>?.mapToEntityNews(): List<News> {
    val newList: MutableList<News> = mutableListOf()

    this?.forEach {
        newList.add(
            News(
                image = it.urlToImage ?: "",
                title = it.title ?: "",
                name = it.source?.name ?: "",
                publishedAt = it.publishedAt ?: "",
                url = it.url ?: ""
            )
        )
    }

    return newList
}