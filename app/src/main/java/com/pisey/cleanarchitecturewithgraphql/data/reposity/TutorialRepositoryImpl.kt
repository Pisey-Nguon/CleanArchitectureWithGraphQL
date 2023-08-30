package com.pisey.cleanarchitecturewithgraphql.data.reposity

import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.CustomResult
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.RemoteDataSource
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.RemoteDataSourceImpl
import com.pisey.cleanarchitecturewithgraphql.domain.repository.TutorialRepository
import com.sey.tutorial.LoginMutation
import com.sey.tutorial.MissionQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class TutorialRepositoryImpl:TutorialRepository {
    private val remoteDataSource:RemoteDataSource = RemoteDataSourceImpl()

    override suspend fun login(email:String):Flow<CustomResult<LoginMutation.Data>> = flow{
        val result = remoteDataSource.login(email)
        emit(result)
    }.onStart { emit(CustomResult.Loading) }

    override suspend fun mission(launchId: String): Flow<CustomResult<MissionQuery.Data>> = flow {
        val result = remoteDataSource.mission(launchId)
        emit(result)
    }.onStart { emit(CustomResult.Loading) }


}