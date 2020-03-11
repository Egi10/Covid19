package id.buaja.covid19.di

import id.buaja.covid19.ui.MapsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
 
fun viewModelModule() = module {
    viewModel {
        MapsViewModel(get())
    }
}