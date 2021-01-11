package com.example.timeline.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.timeline.ui.sprites.SpritesFragment
import com.example.timeline.ui.timeline.first.FirstFragment
import com.example.timeline.ui.timeline.fourth.FourthFragment
import com.example.timeline.ui.timeline.second.SecondFragment
import com.example.timeline.ui.timeline.third.ThirdFragment
import com.example.timeline.ui.timeline.timeline.TimeLineFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class FragmentModule {

    @Binds
    abstract fun provideFragmentFactory(factory: MyFragmentFactory): FragmentFactory

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideFirstFragment(): FirstFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideSecondFragment(): SecondFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideThirdFragment(): ThirdFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideFourthFragment(): FourthFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideTimeLineFragment(): TimeLineFragment

    @Binds
    @IntoMap
    @FragmentKey(SpritesFragment::class)
    abstract fun provideSpritesFragment(fragment: SpritesFragment): Fragment
}
