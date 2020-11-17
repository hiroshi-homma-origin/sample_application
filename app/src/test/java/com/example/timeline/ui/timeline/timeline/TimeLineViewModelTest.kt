package com.example.timeline.ui.timeline.timeline

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.timeline.MainApplication
import com.example.timeline.ui.timeline.TestObserver
import com.kotlin.project.data.model.Master
import com.kotlin.project.data.model.Result
import com.kotlin.project.data.model.TimeLineError
import com.kotlin.project.domain.usecase.GetMasterListUseCase
import com.kotlin.project.useForTesting.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("TestFunctionName")
class TimeLineViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMockKs
    lateinit var viewModel: TimeLineViewModel

    @RelaxedMockK
    lateinit var application: MainApplication

    @RelaxedMockK
    lateinit var timeLineDelegate: TimeLineDelegate

    @RelaxedMockK
    lateinit var getMasterListUseCase: GetMasterListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Successful acquisition of data_データの取得に成功`() {
        // arrange
        val currentTabNumber = 1
        // act
        viewModel.setCurrentTab(currentTabNumber)
        // assert
        assert(viewModel.currentTabNumber.value == currentTabNumber)
    }

    @Test
    fun `Failed to get data_データの取得に失敗`() {
        // arrange
        val currentTabNumber = 1
        val observer = TestObserver<List<Master>>()
        viewModel.list.observeForever(observer)

        val resource = Result.Success(TestData.dummyMasterList)
        coEvery {
            getMasterListUseCase.getMasterList()
        } returns resource
        // act
        viewModel.setCurrentTab(currentTabNumber)
        // assert
        observer.await()
        println(observer.get())
        assert(observer.get() == TestData.dummyMasterList)
    }

    @Test
    fun `error for onRefresh()_データ取得失敗時の処理のテスト`() {
        // arrange
        val currentTabNumber = 1
        val observer = TestObserver<List<Master>>()
        viewModel.list.observeForever(observer)

        val resource = Result.Error(TimeLineError.UnrecognizedError())
        coEvery {
            getMasterListUseCase.getMasterList()
        } returns resource
        // act
        viewModel.setCurrentTab(currentTabNumber)
        // assert
        observer.await()
        assert(observer.get() == TestData.dummyMasterEmptyList)
    }
}
