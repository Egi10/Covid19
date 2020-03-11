package id.buaja.covid19.di

import id.buaja.covid19.network.ApiConfig
import org.koin.dsl.module

/**
 * Copyright 2020 WOWBid Perintis, PT.
 *
 * Created By Julsapargi Nursam 2/17/20
 */
 
fun networkModule() = module {
    single { ApiConfig.createNetworkApi() }
}