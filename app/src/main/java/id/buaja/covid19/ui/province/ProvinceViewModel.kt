package id.buaja.covid19.ui.province

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.network.model.ProvinsiResponse
import id.buaja.covid19.usecase.ConfirmedUseCase
import id.buaja.covid19.util.LoaderState
import id.buaja.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created By Julsapargi Nursam 3/26/20
 */
 
class ProvinceViewModel(private val useCase: ConfirmedUseCase): ViewModel() {
    private val _province = MutableLiveData<List<ProvinsiResponse>>()
    val province: LiveData<List<ProvinsiResponse>> get() = _province

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getConfirmed()
    }

    fun getConfirmed() {
        _state.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                useCase.getProvince()
            }

            when (response) {
                is ResultState.Success -> {
                    _province.postValue(response.data)
                }

                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = false
        }
    }
}