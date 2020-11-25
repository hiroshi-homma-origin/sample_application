package com.kotlin.project.data.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.project.data.model.Results
import javax.inject.Inject

interface GetPokeListRepository {
    fun registerListData(limit: Int, offset: Int)
    fun getPokeList(): MutableLiveData<List<Results>>
}

internal class GetPokeListRepositoryImpl @Inject constructor(
    private val apolloRepository: ApolloRepository
) : GetPokeListRepository {

    override fun registerListData(limit: Int, offset: Int) {
        apolloRepository.registerListData(limit, offset)
    }

    override fun getPokeList(): MutableLiveData<List<Results>> = apolloRepository.getPokeList()
}
