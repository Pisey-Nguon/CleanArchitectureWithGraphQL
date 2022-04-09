package com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote

import com.pisey.cleanarchitecturewithgraphql.data.commons.GraphQLResult
import com.pisey.cleanarchitecturewithgraphql.domain.models.LoginResponse
import com.pisey.cleanarchitecturewithgraphql.domain.models.MissionResponse

interface RemoteDataSource {

    suspend fun login(email:String):GraphQLResult<LoginResponse>

    suspend fun mission(launchId:String):GraphQLResult<MissionResponse>
}