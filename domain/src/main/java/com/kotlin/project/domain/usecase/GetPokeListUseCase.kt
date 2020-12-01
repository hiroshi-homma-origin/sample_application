package com.kotlin.project.domain.usecase

import androidx.lifecycle.LiveData
import com.kotlin.project.data.model.Results
import com.kotlin.project.data.repository.GetPokeListRepository
import javax.inject.Inject

interface GetPokeListUseCase {
    fun pokeList(limit: Int, offset: Int): LiveData<List<Results>>
}

class GetPokeListUseCaseImpl @Inject constructor(
    private val getPokeListRepository: GetPokeListRepository
) : GetPokeListUseCase {
    override fun pokeList(limit: Int, offset: Int): LiveData<List<Results>> =
        getPokeListRepository.pokeList(limit, offset)
}
