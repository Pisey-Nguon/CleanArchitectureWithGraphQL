package com.pisey.cleanarchitecturewithgraphql.presentation.pages.mission

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.CustomResult
import com.pisey.cleanarchitecturewithgraphql.data.reposity.TutorialRepositoryImpl
import com.sey.tutorial.MissionQuery
import kotlinx.coroutines.launch

class MissionViewModel:ViewModel() {
    private val tutorialRepository = TutorialRepositoryImpl()
    val missionLiveData = MutableLiveData<CustomResult<MissionQuery.Data>>()

    fun getMission(launchId:String){
        viewModelScope.launch {
            tutorialRepository.mission(launchId).collect{
                missionLiveData.postValue(it)
            }
        }
    }
}