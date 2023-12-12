package com.pisey.cleanarchitecturewithgraphql.data.reposity

import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.CustomResult
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.RemoteDataSource
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.RemoteDataSourceImpl
import com.pisey.cleanarchitecturewithgraphql.domain.repository.TutorialRepository
import com.sey.tutorial.LoginMutation
import com.sey.tutorial.MissionQuery
class TutorialRepositoryImpl:TutorialRepository {
    private val remoteDataSource:RemoteDataSource = RemoteDataSourceImpl()

    override suspend fun login(email:String):CustomResult<LoginMutation.Data>{
        return remoteDataSource.login(email)
    }

    override suspend fun mission(launchId: String): CustomResult<MissionQuery.Data> {
        return remoteDataSource.mission(launchId)

    }


}