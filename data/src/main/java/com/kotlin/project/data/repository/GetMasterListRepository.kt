package com.kotlin.project.data.repository

import com.kotlin.project.data.api.TimeLineApi
import com.kotlin.project.data.model.Master
import javax.inject.Inject

interface GetMasterListRepository {
    suspend fun getMasterList(): List<Master>
}

internal class GetMasterListRepositoryImpl @Inject constructor(
    private val timeLineApi: TimeLineApi
) : GetMasterListRepository {

    override suspend fun getMasterList(): List<Master> {
        return timeLineApi.getMasterList()
    }
}
