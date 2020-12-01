package com.example.timeline.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.project.data.model.Results
import com.kotlin.project.data.model.TimeLineStatus
import com.kotlin.project.domain.usecase.GetPokeListUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    application: Application,
    private val getPokeListUseCase: GetPokeListUseCase
) : AndroidViewModel(application), LifecycleObserver {

    var spanCount = 3

    private val _timeLineStatus = MutableLiveData<TimeLineStatus>()
    val timeLineStatus: LiveData<TimeLineStatus> = _timeLineStatus

    fun onRefresh() {
        pokeList()
    }

    fun pokeList(): LiveData<List<Results>> {
        return getPokeListUseCase.pokeList(150, 1)
    }
}
