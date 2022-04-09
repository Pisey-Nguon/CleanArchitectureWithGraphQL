package com.pisey.cleanarchitecturewithgraphql.utils

import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.composeJsonResponse
import com.apollographql.apollo3.api.json.buildJsonString
import com.google.gson.Gson

fun <R : Operation.Data,T:Any>ApolloResponse<R>.toResponseModel(classOfT:Class<T>):T{
    val jsonResponse = buildJsonString {
        this@toResponseModel.operation.composeJsonResponse(this, this@toResponseModel.data!!)
    }
    return Gson().fromJson(jsonResponse,classOfT)
}
