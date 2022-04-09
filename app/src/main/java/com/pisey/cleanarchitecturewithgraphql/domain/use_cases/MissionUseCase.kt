package com.pisey.cleanarchitecturewithgraphql.domain.use_cases

import com.pisey.cleanarchitecturewithgraphql.data.reposity.AppRepositoryImpl

class MissionUseCase {
    suspend operator fun invoke(launchId:String) = AppRepositoryImpl.mission(launchId)
}