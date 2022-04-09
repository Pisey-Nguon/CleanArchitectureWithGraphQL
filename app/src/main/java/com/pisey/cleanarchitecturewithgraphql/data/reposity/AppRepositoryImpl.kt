package com.pisey.cleanarchitecturewithgraphql.data.reposity

import com.pisey.cleanarchitecturewithgraphql.data.commons.GraphQLResult
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.RemoteDataSource
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.RemoteDataSourceImpl
import com.pisey.cleanarchitecturewithgraphql.domain.models.LoginResponse
import com.pisey.cleanarchitecturewithgraphql.domain.models.MissionResponse
import com.pisey.cleanarchitecturewithgraphql.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

object AppRepositoryImpl:AppRepository {
    //It's can have localDataSource but i implement only remote data source
    private val remoteDataSource:RemoteDataSource = RemoteDataSourceImpl()

    override suspend fun login(email:String):Flow<GraphQLResult<LoginResponse>> = flow{
        val result = remoteDataSource.login(email)
        emit(result)
    }.onStart { emit(GraphQLResult.Loading) }

    override suspend fun mission(launchId: String): Flow<GraphQLResult<MissionResponse>> = flow {
        val result = remoteDataSource.mission(launchId)
        emit(result)
    }.onStart { emit(GraphQLResult.Loading) }


}