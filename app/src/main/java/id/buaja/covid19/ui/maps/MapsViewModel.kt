package id.buaja.covid19.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.network.model.ResponseConfirmed
import id.buaja.covid19.domain.confirmed.ConfirmedUseCase
import id.buaja.covid19.util.LoaderState
import id.buaja.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsViewModel(private val useCase: ConfirmedUseCase) : ViewModel() {
    private val _confirmated = MutableLiveData<List<ResponseConfirmed>>()
    val confirmated: LiveData<List<ResponseConfirmed>> get() = _confirmated

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getConfirmed() {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getConfirmed()
            }

            when (response) {
                is ResultState.Success -> {
                    _confirmated.postValue(response.data as List<ResponseConfirmed>)
                }

                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = LoaderState.HideLoading
        }
    }
}