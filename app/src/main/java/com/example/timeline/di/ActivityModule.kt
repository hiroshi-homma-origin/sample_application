package com.example.timeline.di

import com.example.timeline.ui.RootActivity
import com.example.timeline.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeRootActivity(): RootActivity
}
