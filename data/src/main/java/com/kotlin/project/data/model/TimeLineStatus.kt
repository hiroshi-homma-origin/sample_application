package com.kotlin.project.data.model

sealed class TimeLineStatus {
    object SUCCESS : TimeLineStatus()
    object ERROR : TimeLineStatus()
    object LOADING : TimeLineStatus()
    object RELOADING : TimeLineStatus()
}
