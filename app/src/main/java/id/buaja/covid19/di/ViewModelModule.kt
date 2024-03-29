package id.buaja.covid19.di

import id.buaja.covid19.ui.maps.MapsViewModel
import id.buaja.covid19.ui.news.NewsViewModel
import id.buaja.covid19.ui.province.ProvinceViewModel
import id.buaja.covid19.ui.statistic.StatisticViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
 
val viewModelModule = module {
    viewModel { MapsViewModel(get()) }
    viewModel { ProvinceViewModel(get()) }
    viewModel { NewsViewModel(get()) }
    viewModel { StatisticViewModel() }
}