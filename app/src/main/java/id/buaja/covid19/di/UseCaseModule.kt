/*
 * Copyright 2020 buaja, id.
 * Created By Julsapargi Nursam 3/2/20 2:38 PM
 * Last modified 3/2/20 2:38 PM
 */

package id.buaja.covid19.di

import id.buaja.covid19.domain.usecase.GetLocationProvinceAndTotalCasesUseCase
import id.buaja.covid19.domain.usecase.GetProvinceCovidUseCase
import id.buaja.covid19.domain.usecase.GetNewsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNewsUseCase(get()) }

    factory { GetProvinceCovidUseCase(get()) }
    factory { GetLocationProvinceAndTotalCasesUseCase(get()) }
}