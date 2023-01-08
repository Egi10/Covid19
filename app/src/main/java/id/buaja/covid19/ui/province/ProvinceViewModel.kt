package id.buaja.covid19.ui.province

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.domain.usecase.GetProvinceCovidUseCase
import id.buaja.covid19.domain.usecase.model.ProvinceCovid
import id.buaja.covid19.util.network.ResultState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created By Julsapargi Nursam 3/26/20
 */

class ProvinceViewModel(
    private val getProvinceCovidUseCase: GetProvinceCovidUseCase
) : ViewModel() {
    private val _province = MutableLiveData<ProvinceCovid>()
    val province: LiveData<ProvinceCovid> get() = _province

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getConfirmed()
    }

    fun getConfirmed() {
        _state.value = true
        viewModelScope.launch() {
            getProvinceCovidUseCase.invoke()
                .collectLatest {
                    when (it) {
                        is ResultState.Success -> {
                            _province.postValue(it.data)
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