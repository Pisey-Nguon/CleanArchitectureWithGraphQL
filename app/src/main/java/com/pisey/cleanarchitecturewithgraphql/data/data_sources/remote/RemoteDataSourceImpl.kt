package com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote

import com.sey.tutorial.LoginMutation
import com.sey.tutorial.MissionQuery

class RemoteDataSourceImpl:RemoteDataSource {
    override suspend fun login(email: String): CustomResult<LoginMutation.Data> {
        return apolloTutorialClient.mutation(LoginMutation(email)).awaitResult()
    }

    override suspend fun mission(launchId: String): CustomResult<MissionQuery.Data> {
        return apolloTutorialClient.query(MissionQuery(launchId)).awaitResult()
    }
}