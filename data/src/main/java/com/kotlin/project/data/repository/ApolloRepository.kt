package com.kotlin.project.data.repository

import PokemonListQuery
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.kotlin.project.data.di.NetworkModule.provideApollo
import com.kotlin.project.data.model.Results
import com.kotlin.project.data.model.transform
import timber.log.Timber
import javax.inject.Inject

interface ApolloRepository {
    fun registerListData(limit: Int, offset: Int)
    fun getPokeList(): MutableLiveData<List<Results>>
}

class ApolloRepositoryImpl @Inject constructor() : ApolloRepository {
    val mList = mutableListOf<Results>()
    val mLiveData: MutableLiveData<List<Results>> = MutableLiveData()

    override fun registerListData(limit: Int, offset: Int) {
        provideApollo()
            .query(PokemonListQuery.builder().limit(limit).offset(offset).build())
            .enqueue(object : ApolloCall.Callback<PokemonListQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    // Failure
                    Timber.d("checkCheckCallBackFailure:$e")
                }

                override fun onResponse(response: Response<PokemonListQuery.Data>) {
                    // Success
                    response.data?.pokemons()?.results()?.map {
                        mList.add(it.transform())
                    }
                    Timber.d("checkCheckCallBackSuccess1:${mList.size}")
                    mLiveData.postValue(mList)
                }
            })
    }

    override fun getPokeList(): MutableLiveData<List<Results>> = mLiveData
}
