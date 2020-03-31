/*
 * Copyright 2020 buaja, id.
 * Created By Julsapargi Nursam 3/2/20 2:38 PM
 * Last modified 3/2/20 2:38 PM
 */

package id.buaja.covid19.di

import id.buaja.covid19.usecase.confirmed.ConfirmedUseCase
import id.buaja.covid19.usecase.news.NewsUseCase
import id.buaja.covid19.usecase.timeline.TimeLineUseCase
import org.koin.dsl.module

fun useCaseModule() = module {
    factory { ConfirmedUseCase(get()) }
    factory { NewsUseCase(get()) }
    factory { TimeLineUseCase(get()) }
}