package id.buaja.covid19.di

import id.buaja.covid19.repository.confirmed.ConfirmedRepository
import id.buaja.covid19.repository.confirmed.ConfirmedRepositoryImpl
import id.buaja.covid19.repository.news.NewsRepository
import id.buaja.covid19.repository.news.NewsRepositoryImpl
import id.buaja.covid19.repository.timeline.TimeLineRepository
import id.buaja.covid19.repository.timeline.TimeLineRepositoryImpl
import org.koin.dsl.module
 
fun repositoryModule() = module {
    single<ConfirmedRepository> {
        ConfirmedRepositoryImpl(
            get()
        )
    }
    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }
    single<TimeLineRepository> {
        TimeLineRepositoryImpl(get())
    }
}