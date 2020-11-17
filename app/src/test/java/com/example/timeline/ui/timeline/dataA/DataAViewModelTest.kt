package com.example.timeline.ui.timeline.dataA

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.timeline.MainApplication
import com.example.timeline.ui.timeline.TestObserver
import com.kotlin.project.data.model.Result
import com.kotlin.project.data.model.TimeLineData
import com.kotlin.project.data.model.TimeLineError
import com.kotlin.project.data.model.TimeLineStatus
import com.kotlin.project.domain.usecase.GetTimeLineListUseCase
import com.kotlin.project.useForTesting.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("TestFunctionName")
class DataAViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMockKs
    lateinit var viewModel: DataAViewModel

    @RelaxedMockK
    lateinit var application: MainApplication

    @RelaxedMockK
    lateinit var getTimeLineListUseCase: GetTimeLineListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Successful acquisition of data_データの取得に成功`() {
        // arrange
        val path = "dataA.json"
        val observerList = TestObserver<List<TimeLineData>>()
        val observerStatus = TestObserver<TimeLineStatus>()
        viewModel.list.observeForever(observerList)
        viewModel.timeLineStatus.observeForever(observerStatus)

        val resource = Result.Success(TestData.dummyTimeLineList)
        coEvery {
            getTimeLineListUseCase.getTimeLineList(path)
        } returns resource
        // act
        viewModel.setPath(path)
        // assert
        observerList.await()
        println(observerStatus.get())
        assert(observerStatus.get() == TimeLineStatus.SUCCESS)
        assert(observerList.get() == TestData.dummyTimeLineList)
    }

    @Test
    fun `Failed to get data_データの取得に失敗`() {
        // arrange
        val path = "dataA.json"
        val observerList = TestObserver<List<TimeLineData>>()
        val observerStatus = TestObserver<TimeLineStatus>()
        viewModel.list.observeForever(observerList)
        viewModel.timeLineStatus.observeForever(observerStatus)

        val resource = Result.Error(TimeLineError.UnrecognizedError())
        coEvery {
            getTimeLineListUseCase.getTimeLineList(path)
        } returns resource
        // act
        viewModel.setPath(path)
        // assert
        observerList.await()
        println(observerStatus.get())
        assert(observerStatus.get() == TimeLineStatus.ERROR)
        assert(observerList.get() == TestData.dummyTimeLineEmptyList)
    }

    @Test
    fun `Successful refresh of data_データの更新に成功`() {
        // arrange
        val path = "dataA.json"
        val observerList = TestObserver<List<TimeLineData>>()
        val observerStatus = TestObserver<TimeLineStatus>()
        viewModel.list.observeForever(observerList)
        viewModel.timeLineStatus.observeForever(observerStatus)

        val resource = Result.Success(TestData.dummyTimeLineList)
        coEvery {
            getTimeLineListUseCase.getTimeLineList(path)
        } returns resource
        // act
        viewModel.setPath(path)
        viewModel.onRefresh()
        // assert
        observerList.await()
        println(observerStatus.get())
        assert(observerStatus.get() == TimeLineStatus.SUCCESS)
        assert(observerList.get() == TestData.dummyTimeLineList)
    }

    @Test
    fun `Failed to refresh data_データの取得に失敗`() {
        // arrange
        val path = "dataA.json"
        val observerList = TestObserver<List<TimeLineData>>()
        val observerStatus = TestObserver<TimeLineStatus>()
        viewModel.list.observeForever(observerList)
        viewModel.timeLineStatus.observeForever(observerStatus)

        val resource = Result.Error(TimeLineError.UnrecognizedError())
        coEvery {
            getTimeLineListUseCase.getTimeLineList(path)
        } returns resource
        // act
        viewModel.setPath(path)
        viewModel.onRefresh()
        // assert
        observerList.await()
        println(observerStatus.get())
        assert(observerStatus.get() == TimeLineStatus.ERROR)
        assert(observerList.get() == TestData.dummyTimeLineEmptyList)
    }
}
