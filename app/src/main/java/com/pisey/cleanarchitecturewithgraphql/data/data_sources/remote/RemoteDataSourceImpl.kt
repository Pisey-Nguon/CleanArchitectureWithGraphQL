package com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote

import com.pisey.cleanarchitecturewithgraphql.data.commons.Apollo
import com.pisey.cleanarchitecturewithgraphql.data.commons.DataSourceException
import com.pisey.cleanarchitecturewithgraphql.data.commons.GraphQLResult
import com.pisey.cleanarchitecturewithgraphql.domain.models.LoginResponse
import com.pisey.cleanarchitecturewithgraphql.domain.models.MissionResponse
import com.pisey.cleanarchitecturewithgraphql.utils.toResponseModel

class RemoteDataSourceImpl:RemoteDataSource {
    override suspend fun login(email: String): GraphQLResult<LoginResponse> {
        return try {
            val result = Apollo.login(email).execute()
            if (result.hasErrors()){
                GraphQLResult.Error(DataSourceException.Server(result.errors?.first()))
            }else{
                GraphQLResult.Success(result.toResponseModel(LoginResponse::class.java))
            }
        }catch (e:Exception){
            GraphQLResult.Error(DataSourceException.Unexpected(e.message.toString()))
        }
    }

    override suspend fun mission(launchId: String): GraphQLResult<MissionResponse> {
        return try {
            val result = Apollo.mission(launchId).execute()
            if(result.hasErrors()){
                GraphQLResult.Error(DataSourceException.Server(result.errors?.first()))
            }else{
                GraphQLResult.Success(result.toResponseModel(MissionResponse::class.java))
            }
        }catch (e:Exception){
            GraphQLResult.Error(DataSourceException.Unexpected(e.message.toString()))
        }
    }
}