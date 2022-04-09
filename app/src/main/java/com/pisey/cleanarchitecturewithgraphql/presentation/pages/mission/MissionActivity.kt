package com.pisey.cleanarchitecturewithgraphql.presentation.pages.mission

import android.widget.Toast
import com.bumptech.glide.Glide
import com.pisey.cleanarchitecturewithgraphql.data.commons.onError
import com.pisey.cleanarchitecturewithgraphql.data.commons.onLoading
import com.pisey.cleanarchitecturewithgraphql.data.commons.onSuccess
import com.pisey.cleanarchitecturewithgraphql.databinding.ActivityMissionBinding
import com.pisey.cleanarchitecturewithgraphql.utils.BaseActivity

class MissionActivity : BaseActivity<ActivityMissionBinding, MissionViewModel>(ActivityMissionBinding::inflate,
    MissionViewModel::class.java){
    companion object{
        val TAG = this::class.java.name.split(".").last()
    }

    override fun onInitValue() {

    }

    override fun onInitEventClick() {

    }

    override fun onInitEventViewModel() {
        viewModel.resultMission.observe(this){ graphQLResult ->
            graphQLResult.onLoading {
                showLoading()
            }
            graphQLResult.onSuccess {
                hideLoading()
                binding.txtMissionName.text = it.data?.launch?.mission?.name
                Glide.with(this).load(it.data?.launch?.mission?.missionPatch).into(binding.ivMissionPatch)
            }
            graphQLResult.onError {
                hideLoading()
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onInitEventOther() {

    }

    override fun onReady() {
        viewModel.mission(launchId = "12")
    }

}