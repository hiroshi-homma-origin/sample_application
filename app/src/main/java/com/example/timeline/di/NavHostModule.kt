package com.example.timeline.di

import com.example.timeline.InjectingNavHostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavHostModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun navHostFragmentInjector(): InjectingNavHostFragment
}
