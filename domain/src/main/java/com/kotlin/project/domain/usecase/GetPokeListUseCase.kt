package com.kotlin.project.domain.usecase

import androidx.lifecycle.LiveData
import com.kotlin.project.data.model.Results
import com.kotlin.project.data.repository.GetPokeListRepository
import javax.inject.Inject

interface GetPokeListUseCase {
    fun registerListData(limit: Int, offset: Int)
    fun getPokeList(): LiveData<List<Results>>
}

class GetPokeListUseCaseImpl @Inject constructor(
    private val getPokeListRepository: GetPokeListRepository
) : GetPokeListUseCase {
    override fun registerListData(limit: Int, offset: Int) =
        getPokeListRepository.registerListData(limit, offset)

    override fun getPokeList(): LiveData<List<Results>> = getPokeListRepository.getPokeList()
}
