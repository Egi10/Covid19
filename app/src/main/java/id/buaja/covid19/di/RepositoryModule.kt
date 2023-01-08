package id.buaja.covid19.di

import id.buaja.covid19.domain.repository.CovidRepository
import id.buaja.covid19.data.repository.CovidRepositoryImpl
import id.buaja.covid19.domain.repository.NewsRepository
import id.buaja.covid19.data.repository.NewsRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<CovidRepository> {
        CovidRepositoryImpl(get(), get(named(CovidDispatchers.IO)))
    }
    single<NewsRepository> {
        NewsRepositoryImpl(get(), get(named(CovidDispatchers.IO)))
    }
}