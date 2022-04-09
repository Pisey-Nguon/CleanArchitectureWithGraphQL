package com.pisey.cleanarchitecturewithgraphql.presentation.pages.home

import android.content.Intent
import android.widget.Toast
import com.pisey.cleanarchitecturewithgraphql.data.commons.User
import com.pisey.cleanarchitecturewithgraphql.data.commons.onError
import com.pisey.cleanarchitecturewithgraphql.data.commons.onLoading
import com.pisey.cleanarchitecturewithgraphql.data.commons.onSuccess
import com.pisey.cleanarchitecturewithgraphql.databinding.ActivityMainBinding
import com.pisey.cleanarchitecturewithgraphql.presentation.pages.mission.MissionActivity
import com.pisey.cleanarchitecturewithgraphql.utils.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate,
    MainViewModel::class.java) {
    companion object{
        val TAG = this::class.java.name.split(".").last()
    }

    override fun onInitValue() {

    }
    override fun onInitEventClick() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.editEmail.text.toString())
        }
    }

    override fun onInitEventViewModel() {
        viewModel.resultLogin.observe(this){ graphQLResult ->
            graphQLResult.onLoading {
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }
            graphQLResult.onSuccess {
                it.data?.login?.token?.let { token -> User.setToken(this, token) }
                finish()
                val intent = Intent(this,MissionActivity::class.java)
                startActivity(intent)
            }
            graphQLResult.onError {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onInitEventOther() {

    }

    override fun onReady() {

    }


}