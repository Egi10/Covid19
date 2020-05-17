package id.buaja.covid19.di

import id.buaja.covid19.data.remote.NewsRemoteDataSource
import org.koin.dsl.module

/**
 * Created By Julsapargi Nursam 5/17/20
 */
 
val remoteDataSource = module {
    single { NewsRemoteDataSource(get()) }
}