package com.pisey.cleanarchitecturewithgraphql.presentation.pages.home

import android.content.Intent
import android.os.Bundle
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.local.UserPref
import com.pisey.cleanarchitecturewithgraphql.databinding.ActivityMainBinding
import com.pisey.cleanarchitecturewithgraphql.presentation.pages.mission.MissionActivity
import com.pisey.cleanarchitecturewithgraphql.utils.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate, MainViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loginLiveData.validateResultToAny {
            it.login?.token?.let { token -> UserPref.setToken(this, token) }
            finish()
            val intent = Intent(this,MissionActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.editEmail.text.toString())
        }
    }

}