package com.kotlin.project.data.di

import com.kotlin.project.data.api.TimeLineApi
import com.kotlin.project.data.repository.GetMasterListRepository
import com.kotlin.project.data.repository.GetMasterListRepositoryImpl
import com.kotlin.project.data.repository.GetTimeLineListRepository
import com.kotlin.project.data.repository.GetTimeLineListRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideGetPokemonListRepository(
        timeLineApi: TimeLineApi
    ): GetMasterListRepository {
        return GetMasterListRepositoryImpl(timeLineApi)
    }

    @Provides
    fun provideGetTimeLineListRepository(
        timeLineApi: TimeLineApi
    ): GetTimeLineListRepository {
        return GetTimeLineListRepositoryImpl(timeLineApi)
    }
}
