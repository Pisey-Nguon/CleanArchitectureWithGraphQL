package com.pisey.cleanarchitecturewithgraphql.domain.repository

import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.CustomResult
import com.sey.tutorial.LoginMutation
import com.sey.tutorial.MissionQuery
import kotlinx.coroutines.flow.Flow

interface TutorialRepository {
    suspend fun login(email:String): Flow<CustomResult<LoginMutation.Data>>

    suspend fun mission(launchId:String):Flow<CustomResult<MissionQuery.Data>>
}