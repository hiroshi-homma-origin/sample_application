package com.kotlin.project.data.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.project.data.model.Results
import javax.inject.Inject

interface GetPokeListRepository {
    fun pokeList(limit: Int, offset: Int): MutableLiveData<List<Results>>
//    fun getPokeList(): MutableLiveData<List<Results>>
}

internal class GetPokeListRepositoryImpl @Inject constructor(
    private val apolloRepository: ApolloRepository
) : GetPokeListRepository {

    override fun pokeList(limit: Int, offset: Int): MutableLiveData<List<Results>> =
        apolloRepository.pokeList(limit, offset)

//    override fun getPokeList(): MutableLiveData<List<Results>> = apolloRepository.getPokeList()
}
