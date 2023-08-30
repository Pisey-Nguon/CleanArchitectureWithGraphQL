package com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote

import com.pisey.cleanarchitecturewithgraphql.MyApplication
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.local.UserPref
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", UserPref.getToken(MyApplication.appContext!!) ?: "")
        return chain.proceed(request.build())
    }
}