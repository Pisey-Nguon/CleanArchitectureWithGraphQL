package com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.pisey.cleanarchitecturewithgraphql.BuildConfig
import okhttp3.OkHttpClient

private var instanceTutorial: ApolloClient? = null
private var instanceUser: ApolloClient? = null
private var instanceOrder: ApolloClient? = null
private var instanceLocation: ApolloClient? = null

val apolloTutorialClient:ApolloClient
    get() {
        if (instanceTutorial != null) {
            return instanceTutorial!!
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
        instanceTutorial = ApolloClient.Builder()
            .serverUrl(BuildConfig.apiService)
            .okHttpClient(okHttpClient)
            .build()
        return instanceTutorial!!
    }

