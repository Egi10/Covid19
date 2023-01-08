package id.buaja.covid19.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.domain.usecase.GetLocationProvinceAndTotalCasesUseCase
import id.buaja.covid19.domain.usecase.model.LocationProvinceWithTotalCases
import id.buaja.covid19.util.network.LoaderState
import id.buaja.covid19.util.network.ResultState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MapsViewModel(
    private val getLocationProvinceAndTotalCasesUseCase: GetLocationProvinceAndTotalCasesUseCase
) : ViewModel() {
    private val _confirmated = MutableLiveData<LocationProvinceWithTotalCases>()
    val confirmated: LiveData<LocationProvinceWithTotalCases> get() = _confirmated

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getConfirmed() {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            getLocationProvinceAndTotalCasesUseCase.invoke()
                .collectLatest {
                    when (it) {
                        is ResultState.Success -> {
                            _confirmated.postValue(it.data)
                        }

                        is ResultState.Error -> {
                            _error.postValue(it.error)
                        }

                        is ResultState.Message -> {
                            _error.postValue(it.message)
                        }
                    }
                    _state.value = LoaderState.HideLoading
                }
        }
    }
}