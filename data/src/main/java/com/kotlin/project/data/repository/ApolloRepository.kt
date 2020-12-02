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
    fun pokeList(limit: Int, offset: Int): MutableLiveData<List<Results>>
}

class ApolloRepositoryImpl @Inject constructor() : ApolloRepository {
    var mList = mutableListOf<Results>()
    val mLiveData: MutableLiveData<List<Results>> = MutableLiveData()

    override fun pokeList(limit: Int, offset: Int): MutableLiveData<List<Results>> {
        runCatching {
            provideApollo()
                .query(PokemonListQuery.builder().limit(limit).offset(offset).build())
                .enqueue(object : ApolloCall.Callback<PokemonListQuery.Data>() {
                    override fun onFailure(e: ApolloException) {
                        // Failure
                        Timber.d("checkCheckCallBackFailure:$e")
                    }

                    override fun onResponse(response: Response<PokemonListQuery.Data>) {
                        // Success
                        if (mList.size == 0) {
                            response.data?.pokemons()?.results()?.map {
                                mList.add(it.transform())
                            }
                        }
                        Timber.d("checkCheckCallBackSuccess1:${mList.size}")
                        mLiveData.postValue(mList)
                    }
                })
        }.fold(
            onSuccess = {
                return mLiveData
            },
            onFailure = {
                return mLiveData
            }
        )
    }
}
