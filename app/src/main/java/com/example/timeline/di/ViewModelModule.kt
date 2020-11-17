package com.example.timeline.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timeline.ui.detail.DetailViewModel
import com.example.timeline.ui.timeline.all.AllViewModel
import com.example.timeline.ui.timeline.dataA.DataAViewModel
import com.example.timeline.ui.timeline.dataB.DataBViewModel
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
    @ViewModelKey(AllViewModel::class)
    fun provideAllViewModel(viewModel: AllViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DataAViewModel::class)
    fun provideMenViewModel(viewModel: DataAViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DataBViewModel::class)
    fun provideWomenViewModel(viewModel: DataBViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TimeLineViewModel::class)
    fun provideTimeLineViewModel(viewModel: TimeLineViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun provideDetailViewModel(viewModel: DetailViewModel): ViewModel
}
