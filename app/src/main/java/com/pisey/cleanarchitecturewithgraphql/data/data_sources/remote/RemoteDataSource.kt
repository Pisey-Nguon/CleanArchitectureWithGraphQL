package com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote

import com.sey.tutorial.LoginMutation
import com.sey.tutorial.MissionQuery

interface RemoteDataSource {

    suspend fun login(email:String): CustomResult<LoginMutation.Data>

    suspend fun mission(launchId:String): CustomResult<MissionQuery.Data>
}