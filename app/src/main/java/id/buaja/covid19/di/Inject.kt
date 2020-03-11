package id.buaja.covid19.di

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

object Inject {
    private val modules: List<Module> = listOf(
        networkModule(),
        repositoryModule(),
        viewModelModule(),
        useCaseModule()
    )

    fun init() = loadKoinModules(
        modules
    )
}