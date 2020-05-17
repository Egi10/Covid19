package id.buaja.covid19.di

import id.buaja.covid19.data.repository.confirmed.ConfirmedRepository
import id.buaja.covid19.data.repository.confirmed.ConfirmedRepositoryImpl
import id.buaja.covid19.data.repository.news.NewsRepository
import id.buaja.covid19.data.repository.news.NewsRepositoryImpl
import id.buaja.covid19.data.repository.timeline.TimeLineRepository
import id.buaja.covid19.data.repository.timeline.TimeLineRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ConfirmedRepository> {
        ConfirmedRepositoryImpl(get())
    }
    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }
    single<TimeLineRepository> {
        TimeLineRepositoryImpl(get())
    }
}