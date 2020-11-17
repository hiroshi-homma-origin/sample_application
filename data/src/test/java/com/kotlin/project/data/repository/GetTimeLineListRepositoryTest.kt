package com.kotlin.project.data.repository

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.kotlin.project.data.BuildConfig
import com.kotlin.project.data.api.TimeLineApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Suppress("TestFunctionName")
@ExperimentalCoroutinesApi
class GetTimeLineListRepositoryTest {

    private var mockWebServer = MockWebServer()
    private lateinit var timeLineApi: TimeLineApi

    @Before
    fun setup() {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.API_READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
        timeLineApi = Retrofit.Builder()
            .baseUrl(BuildConfig.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
            .create(TimeLineApi::class.java)
        mockWebServer.start()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetTimeLineListSizeCheck_All() = runBlocking {
        val path = "all.json"
        val checkSize = 150
        val r = GetTimeLineListRepositoryImpl(timeLineApi)
        assert(r.getTimeLineList(path).size == checkSize)
    }

    @Test
    fun testGetTimeLineListSizeCheck_Men() = runBlocking {
        val path = "men.json"
        val checkSize = 50
        val r = GetTimeLineListRepositoryImpl(timeLineApi)
        assert(r.getTimeLineList(path).size == checkSize)
    }

    @Test
    fun testGetTimeLineListSizeCheck_Women() = runBlocking {
        val path = "women.json"
        val checkSize = 50
        val r = GetTimeLineListRepositoryImpl(timeLineApi)
        assert(r.getTimeLineList(path).size == checkSize)
    }
}
