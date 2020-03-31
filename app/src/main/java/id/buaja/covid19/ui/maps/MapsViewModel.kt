package id.buaja.covid19.ui.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.network.model.ResponseConfirmed
import id.buaja.covid19.usecase.confirmed.ConfirmedUseCase
import id.buaja.covid19.usecase.timeline.TimeLineUseCase
import id.buaja.covid19.util.LoaderState
import id.buaja.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsViewModel(private val useCase: ConfirmedUseCase, private val timeLineUseCase: TimeLineUseCase) : ViewModel() {
    private val _confirmated = MutableLiveData<List<ResponseConfirmed>>()
    val confirmated: LiveData<List<ResponseConfirmed>> get() = _confirmated

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getTimeLine()
    }

    fun getConfirmed() {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getConfirmed()
            }

            when (response) {
                is ResultState.Success -> {
                    _confirmated.postValue(response.data)
                }

                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = LoaderState.HideLoading
        }
    }

    private fun getTimeLine() {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                timeLineUseCase.getTimeLine()
            }

            when (response) {
                is ResultState.Success -> {
                    Log.d("Sukses", response.data!![3].totalCases.toString())
                }

                is ResultState.Error -> {
                    Log.d("Error", response.error)
                }
            }
            _state.value = LoaderState.HideLoading
        }
    }
}