package id.buaja.covid19.di

import id.buaja.covid19.network.ApiConfig
import org.koin.dsl.module

val networkModule = module {
    single { ApiConfig.createNetworkApi() }
}