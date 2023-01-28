package id.buaja.covid19.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.domain.usecase.GetNewsUseCase
import id.buaja.covid19.ui.news.model.NewsUiState
import id.buaja.covid19.util.network.ResultState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Created By Julsapargi Nursam 3/28/20
 */

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    val uiState: StateFlow<NewsUiState> = getNewsUseCase.invoke().map {
        when(it) {
            is ResultState.Success -> {
                if (it.data.isEmpty()) {
                    NewsUiState.Empty
                } else {
                    NewsUiState.Success(it.data)
                }
            }

            is ResultState.Error -> {
                NewsUiState.Error(it.error)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NewsUiState.Loading
    )
}