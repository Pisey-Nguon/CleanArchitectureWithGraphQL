package com.pisey.cleanarchitecturewithgraphql.presentation.pages.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.CustomResult
import com.pisey.cleanarchitecturewithgraphql.data.reposity.TutorialRepositoryImpl
import com.sey.tutorial.LoginMutation
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private val tutorialRepository = TutorialRepositoryImpl()
    val loginLiveData = MutableLiveData<CustomResult<LoginMutation.Data>>()

    fun login(email:String) = viewModelScope.launch{
        loginLiveData.postValue(tutorialRepository.login(email))
    }
}