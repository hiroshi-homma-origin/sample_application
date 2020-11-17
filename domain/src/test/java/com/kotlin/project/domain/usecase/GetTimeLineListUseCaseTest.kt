package com.kotlin.project.domain.usecase

import com.kotlin.project.data.model.TimeLineError
import com.kotlin.project.data.model.data
import com.kotlin.project.data.repository.GetTimeLineListRepository
import com.kotlin.project.useForTesting.TestData
import com.nhaarman.mockitokotlin2.any
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@Suppress("TestFunctionName")
@ExperimentalCoroutinesApi
class GetTimeLineListUseCaseTest {

    // Overrides Dispatchers.Main used in Coroutines
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun execute_Success() = testDispatcher.runBlockingTest {
        // arrange
        val r: GetTimeLineListRepository = mockk {
            coEvery {
                getTimeLineList(any())
            } returns TestData.dummyTimeLineList
        }
        val u = GetTimeLineListUseCaseImpl(r)
        // act
        val result = u.getTimeLineList(any()).data
        // assert
        assert(result == TestData.dummyTimeLineList)
        coVerify { r.getTimeLineList(any()) }
        confirmVerified(r)
    }

    @Test
    fun execute_Error_UnknownError() = testDispatcher.runBlockingTest {
        // arrange
        val e = TimeLineError.UnrecognizedError()
        // arrange
        val r: GetTimeLineListRepository = mockk {
            coEvery {
                getTimeLineList(any())
            } throws e
        }
        val u = GetTimeLineListUseCaseImpl(r)
        // act
        val result = u.getTimeLineList(any()).data
        // assert
        assert(result == null)
        coVerify { r.getTimeLineList(any()) }
        confirmVerified(r)
    }
}
