package com.example.timeline.di

import com.example.timeline.ui.timeline.timeline.TimeLineDelegate
import com.example.timeline.ui.timeline.timeline.TimeLineDelegateImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DelegateModule {
    @Singleton
    @Provides
    fun provideTopViewModelDelegate(): TimeLineDelegate {
        return TimeLineDelegateImpl()
    }
}
