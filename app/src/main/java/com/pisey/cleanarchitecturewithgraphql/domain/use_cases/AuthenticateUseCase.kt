package com.pisey.cleanarchitecturewithgraphql.domain.use_cases

import com.pisey.cleanarchitecturewithgraphql.data.reposity.AppRepositoryImpl

class AuthenticateUseCase {
    suspend operator fun invoke(email: String) = AppRepositoryImpl.login(email)
}