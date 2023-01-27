package id.buaja.covid19.ui.province

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.domain.usecase.GetProvinceCovidUseCase
import id.buaja.covid19.ui.province.model.ProvinceUiState
import id.buaja.covid19.util.network.ResultState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Created By Julsapargi Nursam 3/26/20
 */

class ProvinceViewModel(
    getProvinceCovidUseCase: GetProvinceCovidUseCase
) : ViewModel() {

    val uiState: StateFlow<ProvinceUiState> = getProvinceCovidUseCase.invoke().map {
        when (it) {
            is ResultState.Success -> {
                if (it.data.provinceCases.isEmpty()) {
                    ProvinceUiState.Empty
                } else {
                    ProvinceUiState.Success(it.data)
                }
            }

            is ResultState.Error -> {
                ProvinceUiState.Error(it.error)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProvinceUiState.Loading
    )
}