package id.buaja.covid19.di

import id.buaja.covid19.data.source.RemoteDataSource
import id.buaja.covid19.data.source.RemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> {
        RemoteDataSourceImpl(get())
    }
}