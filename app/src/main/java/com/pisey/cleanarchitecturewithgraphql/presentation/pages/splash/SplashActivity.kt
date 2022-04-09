package com.pisey.cleanarchitecturewithgraphql.presentation.pages.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.pisey.cleanarchitecturewithgraphql.data.commons.User
import com.pisey.cleanarchitecturewithgraphql.databinding.ActivitySplashBinding
import com.pisey.cleanarchitecturewithgraphql.presentation.pages.home.MainActivity
import com.pisey.cleanarchitecturewithgraphql.presentation.pages.mission.MissionActivity
import com.pisey.cleanarchitecturewithgraphql.utils.BaseActivity


class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>(ActivitySplashBinding::inflate,SplashViewModel::class.java) {
    override fun onStart() {
        super.onStart()
        if(User.getToken(this) == null){
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
                startActivity(Intent(this,MissionActivity::class.java))
            },2000)
        }
    }
    override fun onInitValue() {

    }

    override fun onInitEventClick() {

    }

    override fun onInitEventViewModel() {

    }

    override fun onInitEventOther() {

    }

    override fun onReady() {

    }

}