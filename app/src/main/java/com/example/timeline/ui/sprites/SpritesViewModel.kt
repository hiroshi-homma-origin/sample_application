package com.example.timeline.ui.sprites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.project.data.model.Results
import com.kotlin.project.data.model.TimeLineStatus
import com.kotlin.project.domain.usecase.GetPokeListUseCase
import javax.inject.Inject

class SpritesViewModel @Inject constructor(
    application: Application,
    private val getPokeListUseCase: GetPokeListUseCase
) : AndroidViewModel(application), LifecycleObserver {

    var spanCount = 3
    var screenHasRotated: Boolean = false

    var pList: List<Results> = listOf()

    private val _timeLineStatus = MutableLiveData<TimeLineStatus>()
    val timeLineStatus: LiveData<TimeLineStatus> = _timeLineStatus

    fun onRefresh(limit: Int, offset: Int) {
        pokeList(limit, offset)
    }

    fun pokeList(limit: Int, offset: Int): LiveData<List<Results>> {
        return getPokeListUseCase.pokeList(limit, offset)
    }
}
