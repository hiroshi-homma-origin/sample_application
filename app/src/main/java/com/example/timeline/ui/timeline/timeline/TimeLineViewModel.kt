package com.example.timeline.ui.timeline.timeline

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.project.data.model.Master
import com.kotlin.project.data.model.Result
import com.kotlin.project.domain.usecase.GetMasterListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimeLineViewModel @Inject constructor(
    timeLineDelegate: TimeLineDelegate,
    application: Application,
    private val getMasterListUseCase: GetMasterListUseCase
) : AndroidViewModel(application), LifecycleObserver, TimeLineDelegate by timeLineDelegate {

    private val _currentTabNumber = MutableLiveData<Int>()
    override val currentTabNumber: LiveData<Int> = _currentTabNumber

    private val _list = MediatorLiveData<List<Master>>()
    val list: LiveData<List<Master>> = _list

    init {
        _list.addSource(_currentTabNumber) {
            fetchData()
        }
    }

    fun setCurrentTab(position: Int) {
        _currentTabNumber.value = position
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.Default) {
            when (val r = getMasterListUseCase.getMasterList()) {
                is Result.Success -> {
                    _list.postValue(r.data)
                }
                is Result.Error -> {
                    _list.postValue(listOf())
                }
            }
        }
    }
}
