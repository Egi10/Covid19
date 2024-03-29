package id.buaja.covid19.di

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

class Modules {
    private val modules: List<Module> = listOf(
        networkModule,
        dataSourceModule,
        repositoryModule,
        viewModelModule,
        useCaseModule,
        dispatcherModule
    )

    init {
        loadKoinModules(
            modules
        )
    }
}