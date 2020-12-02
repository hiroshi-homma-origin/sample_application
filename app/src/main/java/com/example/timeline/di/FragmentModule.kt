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
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @Binds
    fun provideFragmentFactory(factory: MyFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(FirstFragment::class)
    fun provideFirstFragment(fragment: FirstFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SecondFragment::class)
    fun provideSecondFragment(fragment: SecondFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ThirdFragment::class)
    fun provideThirdFragment(fragment: ThirdFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(FourthFragment::class)
    fun provideFourthFragment(fragment: FourthFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(TimeLineFragment::class)
    fun provideTimeLineFragment(fragment: TimeLineFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SpritesFragment::class)
    fun provideSpritesFragment(fragment: SpritesFragment): Fragment
}
