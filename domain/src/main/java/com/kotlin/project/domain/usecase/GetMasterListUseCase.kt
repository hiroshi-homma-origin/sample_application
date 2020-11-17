package com.kotlin.project.domain.usecase

import com.kotlin.project.data.model.Master
import com.kotlin.project.data.model.Result
import com.kotlin.project.data.model.TimeLineError
import com.kotlin.project.data.repository.GetMasterListRepository
import javax.inject.Inject

interface GetMasterListUseCase {
    suspend fun getMasterList(): Result<List<Master>>
}

class GetMasterListUseCaseImpl @Inject constructor(
    private val getMasterListRepository: GetMasterListRepository
) : GetMasterListUseCase {
    override suspend fun getMasterList(): Result<List<Master>> =
        runCatching { getMasterListRepository.getMasterList() }
            .fold(
                onSuccess = { return Result.Success(it) },
                onFailure = { return Result.Error(TimeLineError.UnrecognizedError()) }
            )
}
