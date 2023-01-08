package id.buaja.covid19.ui.statistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.ui.statistic.model.TimeLineItem
import id.buaja.covid19.util.network.LoaderState
import kotlinx.coroutines.launch

/**
 * Created By Julsapargi Nursam 4/15/20
 */

class StatisticViewModel() : ViewModel() {
    private val _statistic = MutableLiveData<List<TimeLineItem>>()
    val statistic: LiveData<List<TimeLineItem>> get() = _statistic

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getTimeLine()
    }

    private fun getTimeLine() {
        _state.value = LoaderState.HideLoading
        viewModelScope.launch() {
            _error.value = "Kami mengalami masalah untuk Sumber Data, jadi untuk sementara feature ini tidak bisa diakses"
        }
    }
}