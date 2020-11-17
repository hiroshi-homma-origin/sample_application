package com.kotlin.project.domain.usecase

import com.kotlin.project.data.model.Result
import com.kotlin.project.data.model.TimeLineData
import com.kotlin.project.data.model.TimeLineError
import com.kotlin.project.data.repository.GetTimeLineListRepository
import javax.inject.Inject

interface GetTimeLineListUseCase {
    suspend fun getTimeLineList(jsonName: String): Result<List<TimeLineData>>
}

class GetTimeLineListUseCaseImpl @Inject constructor(
    private val getTimeLineListRepository: GetTimeLineListRepository
) : GetTimeLineListUseCase {
    override suspend fun getTimeLineList(jsonName: String): Result<List<TimeLineData>> =
        runCatching { getTimeLineListRepository.getTimeLineList(jsonName) }
            .fold(
                onSuccess = { return Result.Success(it) },
                onFailure = { return Result.Error(TimeLineError.UnrecognizedError()) }
            )
}
