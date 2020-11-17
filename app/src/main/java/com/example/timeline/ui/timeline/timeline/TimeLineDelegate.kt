package com.example.timeline.ui.timeline.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface TimeLineDelegate {
    val currentTabNumber: LiveData<Int>
}

class TimeLineDelegateImpl : TimeLineDelegate {
    private val _currentTabNumber = MutableLiveData<Int>()
    override val currentTabNumber: LiveData<Int> = _currentTabNumber
}
