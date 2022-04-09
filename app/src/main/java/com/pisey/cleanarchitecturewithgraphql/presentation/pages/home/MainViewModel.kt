package com.pisey.cleanarchitecturewithgraphql.presentation.pages.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisey.cleanarchitecturewithgraphql.data.commons.GraphQLResult
import com.pisey.cleanarchitecturewithgraphql.domain.models.LoginResponse
import com.pisey.cleanarchitecturewithgraphql.domain.use_cases.AuthenticateUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    val authenticateUseCase = AuthenticateUseCase()
    private val _resultLogin  = MutableLiveData<GraphQLResult<LoginResponse>>()
    val resultLogin:LiveData<GraphQLResult<LoginResponse>> = _resultLogin

    fun login(email:String){
        viewModelScope.launch {
            authenticateUseCase(email).collect {
                _resultLogin.postValue(it)
            }
        }
    }
}