package id.buaja.covid19.ui.news.model

import id.buaja.covid19.domain.usecase.model.News

sealed interface NewsUiState {
    object Loading : NewsUiState
    data class Success(val news: List<News>) : NewsUiState
    object Empty : NewsUiState
    data class Error(val message: String) : NewsUiState
}