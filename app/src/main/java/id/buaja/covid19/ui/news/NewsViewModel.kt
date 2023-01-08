package id.buaja.covid19.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.domain.usecase.GetNewsUseCase
import id.buaja.covid19.domain.usecase.model.News
import id.buaja.covid19.util.network.ResultState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created By Julsapargi Nursam 3/28/20
 */

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getNews()
    }

    fun getNews() {
        _state.value = true
        viewModelScope.launch {
            getNewsUseCase.invoke()
                .collectLatest {
                    when (it) {
                        is ResultState.Success -> {
                            val newsResponse = it.data
                            _news.postValue(newsResponse)
                        }

                        is ResultState.Error -> {
                            _error.postValue(it.error)
                        }

                        is ResultState.Message -> {
                            _error.postValue(it.message)
                        }
                    }
                    _state.value = false
                }
        }
    }
}