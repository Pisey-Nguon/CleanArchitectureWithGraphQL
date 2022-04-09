package com.pisey.cleanarchitecturewithgraphql.data.commons

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.pisey.cleanarchitecturewithgraphql.BuildConfig
import com.pisey.cleanarchitecturewithgraphql.LoginMutation
import com.pisey.cleanarchitecturewithgraphql.MissionQuery
import com.pisey.cleanarchitecturewithgraphql.MyApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object Apollo {
    private var instance: ApolloClient? = null

    //If use micro service let add parameter for any api service to this function
    private fun apolloClient(): ApolloClient {
        if (instance != null) {
            return instance!!
        }

        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", User.getToken(MyApplication.appContext!!) ?: "").build()
                chain.proceed(request)
            }
            .addInterceptor(logging)
            .build()


        instance = ApolloClient.Builder()
            .serverUrl(BuildConfig.apiService)
            .okHttpClient(okHttpClient)
            .build()

        return instance!!
    }


    fun login(email:String):ApolloCall<LoginMutation.Data> = apolloClient().mutation(LoginMutation(email))

    fun mission(launchId:String):ApolloCall<MissionQuery.Data> = apolloClient().query(MissionQuery(launchId))
}
