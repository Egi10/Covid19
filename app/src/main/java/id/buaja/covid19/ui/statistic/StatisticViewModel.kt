package id.buaja.covid19.ui.statistic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.covid19.network.model.timeline.TimeLineItem
import id.buaja.covid19.usecase.timeline.TimeLineUseCase
import id.buaja.covid19.util.LoaderState
import id.buaja.covid19.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created By Julsapargi Nursam 4/15/20
 */

class StatisticViewModel(private val timeLineUseCase: TimeLineUseCase) : ViewModel() {
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
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                timeLineUseCase.getTimeLine()
            }

            when (response) {
                is ResultState.Success -> {
                    _statistic.postValue(response.data)
                }

                is ResultState.Error -> {
                    _error.postValue(response.error)
                }
            }
            _state.value = LoaderState.HideLoading
        }
    }
}