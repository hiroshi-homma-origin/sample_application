package com.kotlin.project.data.repository

import com.kotlin.project.data.api.TimeLineApi
import com.kotlin.project.data.model.TimeLineData
import javax.inject.Inject

interface GetTimeLineListRepository {
    suspend fun getTimeLineList(jsonName: String): List<TimeLineData>
}

internal class GetTimeLineListRepositoryImpl @Inject constructor(
    private val timeLineApi: TimeLineApi
) : GetTimeLineListRepository {
    override suspend fun getTimeLineList(jsonName: String): List<TimeLineData> {
        return timeLineApi.getTimeLineList(jsonName)
    }
}
