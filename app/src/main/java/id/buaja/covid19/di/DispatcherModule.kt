package id.buaja.covid19.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single(named(CovidDispatchers.Default)) {
        Dispatchers.Default
    }

    single(named(CovidDispatchers.Main)) {
        Dispatchers.Main
    }

    single(named(CovidDispatchers.IO)) {
        Dispatchers.IO
    }
}

object CovidDispatchers {
    const val IO = "io"
    const val Main = "main"
    const val Default = "default"
}