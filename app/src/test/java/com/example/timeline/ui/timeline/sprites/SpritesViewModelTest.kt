package com.example.timeline.ui.timeline.sprites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.timeline.MainApplication
import com.example.timeline.ui.sprites.SpritesViewModel
import com.kotlin.project.domain.usecase.GetPokeListUseCase
import io.mockk.MockKAnnotations
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
        // act
        // assert
    }

    @Test
    fun `Failed to get data_データの取得に失敗`() {
        // arrange
        // act
        // assert
    }
}
