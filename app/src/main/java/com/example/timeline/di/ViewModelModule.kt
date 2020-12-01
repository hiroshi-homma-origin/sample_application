package com.example.timeline.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timeline.ui.detail.DetailViewModel
import com.example.timeline.ui.timeline.first.FirstViewModel
import com.example.timeline.ui.timeline.second.SecondViewModel
import com.example.timeline.ui.timeline.fourth.FourthViewModel
import com.example.timeline.ui.timeline.third.ThirdViewModel
import com.example.timeline.ui.timeline.timeline.TimeLineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FirstViewModel::class)
    fun provideFirstViewModel(viewModel: FirstViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondViewModel::class)
    fun provideSecondViewModel(viewModel: SecondViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ThirdViewModel::class)
    fun provideThirdViewModel(viewModel: ThirdViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FourthViewModel::class)
    fun provideFourthViewModel(viewModel: FourthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TimeLineViewModel::class)
    fun provideTimeLineViewModel(viewModel: TimeLineViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun provideDetailViewModel(viewModel: DetailViewModel): ViewModel
}
