package com.pisey.cleanarchitecturewithgraphql.domain.models


data class LoginResponse (var data : LoginData? = LoginData())

data class LoginData (var login : LoginResult? = LoginResult())

data class LoginResult (var token : String? = null)