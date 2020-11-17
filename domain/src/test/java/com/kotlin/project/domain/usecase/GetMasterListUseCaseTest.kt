package com.kotlin.project.domain.usecase

import com.kotlin.project.data.model.TimeLineError
import com.kotlin.project.data.model.data
import com.kotlin.project.data.repository.GetMasterListRepository
import com.kotlin.project.useForTesting.TestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@Suppress("TestFunctionName")
@ExperimentalCoroutinesApi
class GetMasterListUseCaseTest {

    // Overrides Dispatchers.Main used in Coroutines
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Test
    fun execute_Success() = testDispatcher.runBlockingTest {
        // arrange
        val r: GetMasterListRepository = mockk {
            coEvery {
                getMasterList()
            } returns TestData.dummyMasterList
        }
        val u = GetMasterListUseCaseImpl(r)
        // act
        val result = u.getMasterList().data
        // assert
        assert(result == TestData.dummyMasterList)
        coVerify { r.getMasterList() }
        confirmVerified(r)
    }

    @Test
    fun execute_Error_UnknownError() = testDispatcher.runBlockingTest {
        // arrange
        val e = TimeLineError.UnrecognizedError()
        // arrange
        val r: GetMasterListRepository = mockk {
            coEvery {
                getMasterList()
            } throws e
        }
        val u = GetMasterListUseCaseImpl(r)
        // act
        val result = u.getMasterList().data
        // assert
        assert(result == null)
        coVerify { r.getMasterList() }
        confirmVerified(r)
    }
}
