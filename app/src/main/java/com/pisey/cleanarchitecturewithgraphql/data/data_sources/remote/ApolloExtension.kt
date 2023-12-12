package com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.api.Error

sealed class CustomResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : CustomResult<T>()
    data class Failed(val error:Error) : CustomResult<Nothing>()
    data class SomethingWentWrong(val error: Exception) : CustomResult<Nothing>()
}
suspend fun <D : com.apollographql.apollo3.api.Operation.Data> ApolloCall<D>.awaitResult(): CustomResult<D> {
    return try {
        val response = execute()
        if (response.hasErrors()){
            CustomResult.Failed(response.errors!!.first())
        }else{
            CustomResult.Success(response.data!!)
        }
    }catch (e: Exception){
        CustomResult.SomethingWentWrong(e)
    }


}
