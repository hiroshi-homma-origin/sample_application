package com.example.timeline.ui.timeline.fourth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.project.data.model.Result
import com.kotlin.project.data.model.TimeLineData
import com.kotlin.project.data.model.TimeLineStatus
import com.kotlin.project.domain.usecase.GetTimeLineListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FourthViewModel @Inject constructor(
    application: Application,
    private val getTimeLineListUseCase: GetTimeLineListUseCase
) : AndroidViewModel(application), LifecycleObserver {

    var spanCount = 3
    var screenHasRotated: Boolean = false

    private val _timeLineStatus = MutableLiveData<TimeLineStatus>()
    val timeLineStatus: LiveData<TimeLineStatus> = _timeLineStatus

    private val _list = MediatorLiveData<List<TimeLineData>>()
    val list: LiveData<List<TimeLineData>> = _list

    private val path = MutableLiveData<String>()

    init {
        _list.addSource(path) {
            fetchData(it)
        }
    }

    fun setPath(newPath: String) {
        path.value = newPath
    }

    fun onRefresh() {
        path.value?.let {
            fetchData(it, true)
        }
    }

    private fun fetchData(path: String, isPullToRefresh: Boolean = false) {
        _timeLineStatus.postValue(if (isPullToRefresh) TimeLineStatus.RELOADING else TimeLineStatus.LOADING)
        viewModelScope.launch {
            when (val r = getTimeLineListUseCase.getTimeLineList(path)) {
                is Result.Success -> {
                    _list.postValue(r.data)
                    _timeLineStatus.postValue(TimeLineStatus.SUCCESS)
                }
                is Result.Error -> {
                    _list.postValue(listOf())
                    _timeLineStatus.postValue(TimeLineStatus.ERROR)
                }
            }
        }
    }
}
