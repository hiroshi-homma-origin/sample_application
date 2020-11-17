package com.kotlin.project.data.model

sealed class TimeLineError(val alertMessage: String) :
    Throwable() {
    class UnrecognizedError : TimeLineError(alertMessage = "エラーが発生しました。再度お試しください。")
    // ToDo() ↓ add Error...
}
