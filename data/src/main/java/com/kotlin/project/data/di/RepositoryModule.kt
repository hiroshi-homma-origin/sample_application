package com.kotlin.project.data.di

import com.kotlin.project.data.api.TimeLineApi
import com.kotlin.project.data.repository.ApolloRepository
import com.kotlin.project.data.repository.ApolloRepositoryImpl
import com.kotlin.project.data.repository.GetMasterListRepository
import com.kotlin.project.data.repository.GetMasterListRepositoryImpl
import com.kotlin.project.data.repository.GetPokeListRepository
import com.kotlin.project.data.repository.GetPokeListRepositoryImpl
import com.kotlin.project.data.repository.GetTimeLineListRepository
import com.kotlin.project.data.repository.GetTimeLineListRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideGetMasterListRepository(
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

    @Provides
    fun providePokeListRepository(
        apolloRepository: ApolloRepository
    ): GetPokeListRepository {
        return GetPokeListRepositoryImpl(apolloRepository)
    }

    @Provides
    fun provideApolloRepository(): ApolloRepository {
        return ApolloRepositoryImpl()
    }
}
