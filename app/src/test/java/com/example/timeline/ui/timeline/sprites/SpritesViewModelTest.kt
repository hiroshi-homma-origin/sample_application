package com.example.timeline.ui.timeline.sprites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.timeline.MainApplication
import com.example.timeline.ui.sprites.SpritesViewModel
import com.example.timeline.ui.timeline.TestObserver
import com.kotlin.project.data.model.Results
import com.kotlin.project.domain.usecase.GetPokeListUseCase
import com.kotlin.project.useForTesting.TestData.dummyResultsList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("TestFunctionName")
class SpritesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMockKs
    lateinit var viewModel: SpritesViewModel

    @RelaxedMockK
    lateinit var application: MainApplication

    @RelaxedMockK
    lateinit var getPokeListUseCase: GetPokeListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Successful acquisition of data_データの取得に成功`() {
        // arrange
        val limit = 8
        val offset = 1
        val observerList = TestObserver<List<Results>>()
        val pokeLiveData = MutableLiveData(dummyResultsList)
        coEvery {
            getPokeListUseCase.pokeList(any(), any())
        } returns pokeLiveData
        // act
        viewModel.pokeList(limit, offset).observeForever(observerList)
        // assert
        observerList.await()
        println(observerList.get())
        assert(observerList.get() == dummyResultsList)
    }
}
