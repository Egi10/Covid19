package id.buaja.covid19.di

import id.buaja.covid19.repository.ConfirmedRepository
import id.buaja.covid19.repository.ConfirmedRepositoryImpl
import org.koin.dsl.module
 
fun repositoryModule() = module {
    single<ConfirmedRepository> { ConfirmedRepositoryImpl(get()) }
}