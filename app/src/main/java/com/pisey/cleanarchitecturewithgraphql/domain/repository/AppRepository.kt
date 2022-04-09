package com.pisey.cleanarchitecturewithgraphql.domain.repository

import com.pisey.cleanarchitecturewithgraphql.data.commons.GraphQLResult
import com.pisey.cleanarchitecturewithgraphql.domain.models.LoginResponse
import com.pisey.cleanarchitecturewithgraphql.domain.models.MissionResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun login(email:String): Flow<GraphQLResult<LoginResponse>>

    suspend fun mission(launchId:String):Flow<GraphQLResult<MissionResponse>>
}