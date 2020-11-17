package com.example.timeline.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

    private val _title = MutableLiveData<String>()
    val title = _title

    fun setTitle(newtTitle: String) {
        _title.value = newtTitle
    }
}
