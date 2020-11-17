package com.kotlin.project.domain.di

import com.kotlin.project.data.repository.GetMasterListRepository
import com.kotlin.project.data.repository.GetTimeLineListRepository
import com.kotlin.project.domain.usecase.GetMasterListUseCase
import com.kotlin.project.domain.usecase.GetMasterListUseCaseImpl
import com.kotlin.project.domain.usecase.GetTimeLineListUseCase
import com.kotlin.project.domain.usecase.GetTimeLineListUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMasterListUseCase(
        getMasterListRepository: GetMasterListRepository
    ): GetMasterListUseCase {
        return GetMasterListUseCaseImpl(getMasterListRepository)
    }

    @Provides
    fun provideGetTimeLineListUseCase(
        getTimeLineListRepository: GetTimeLineListRepository
    ): GetTimeLineListUseCase {
        return GetTimeLineListUseCaseImpl(getTimeLineListRepository)
    }
}
