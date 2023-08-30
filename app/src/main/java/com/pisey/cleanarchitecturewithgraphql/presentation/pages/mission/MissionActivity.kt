package com.pisey.cleanarchitecturewithgraphql.presentation.pages.mission

import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.pisey.cleanarchitecturewithgraphql.databinding.ActivityMissionBinding
import com.pisey.cleanarchitecturewithgraphql.utils.BaseActivity

class MissionActivity : BaseActivity<ActivityMissionBinding, MissionViewModel>(ActivityMissionBinding::inflate,
    MissionViewModel::class.java){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.missionLiveData.validateResultToStateView(binding.stateView) {
            binding.txtMissionName.text = it.launch?.mission?.name
            Glide.with(this).load(it.launch?.mission?.missionPatch).into(binding.ivMissionPatch)
        }
        binding.stateView.onLoad = { viewModel.getMission("12") }

    }

}