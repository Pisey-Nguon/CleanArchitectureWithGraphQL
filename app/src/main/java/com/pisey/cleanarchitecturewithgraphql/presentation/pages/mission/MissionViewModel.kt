package com.pisey.cleanarchitecturewithgraphql.presentation.pages.mission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pisey.cleanarchitecturewithgraphql.data.commons.GraphQLResult
import com.pisey.cleanarchitecturewithgraphql.domain.models.MissionResponse
import com.pisey.cleanarchitecturewithgraphql.domain.use_cases.MissionUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MissionViewModel:ViewModel() {

    private val missionUseCase = MissionUseCase()
    private val _resultMission = MutableLiveData<GraphQLResult<MissionResponse>>()
    val resultMission:LiveData<GraphQLResult<MissionResponse>> = _resultMission

    fun mission(launchId:String){
        viewModelScope.launch {
            missionUseCase(launchId).collect {
                _resultMission.postValue(it)
            }
        }
    }
}