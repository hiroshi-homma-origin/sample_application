package com.example.timeline.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.timeline.ui.detail.DetailFragment
import com.example.timeline.ui.timeline.all.AllFragment
import com.example.timeline.ui.timeline.dataA.DataAFragment
import com.example.timeline.ui.timeline.dataB.DataBFragment
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
    @FragmentKey(AllFragment::class)
    fun provideAllFragment(fragment: AllFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DataAFragment::class)
    fun provideDataAFragment(fragment: DataAFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DataBFragment::class)
    fun provideDataBFragment(fragment: DataBFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(TimeLineFragment::class)
    fun provideTimeLineFragment(fragment: TimeLineFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailFragment::class)
    fun provideDetailFragment(fragment: DetailFragment): Fragment
}
