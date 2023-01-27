package id.buaja.covid19.ui.province.model

import id.buaja.covid19.domain.usecase.model.ProvinceCovid

sealed interface ProvinceUiState {
    object Loading: ProvinceUiState
    data class Success(
        val province: ProvinceCovid
    ): ProvinceUiState
    object Empty: ProvinceUiState
    data class Error(val message: String): ProvinceUiState
}