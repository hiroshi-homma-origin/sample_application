package com.kotlin.project.data.api

import com.kotlin.project.data.model.Master
import com.kotlin.project.data.model.TimeLineData
import retrofit2.http.GET
import retrofit2.http.Path

interface TimeLineApi {
    @GET("master.json")
    suspend fun getMasterList(): List<Master>

    @GET("{jsonName}")
    suspend fun getTimeLineList(@Path("jsonName") jsonName: String?): List<TimeLineData>
}
