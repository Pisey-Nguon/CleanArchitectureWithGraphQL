package com.pisey.cleanarchitecturewithgraphql.presentation.pages.route

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.pisey.cleanarchitecturewithgraphql.data.commons.User
import com.pisey.cleanarchitecturewithgraphql.presentation.pages.home.MainActivity
import com.pisey.cleanarchitecturewithgraphql.presentation.pages.mission.MissionActivity

class RouteActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ///it working only on android 12 if lower this please custom splash screen by self
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        // Keep the splash screen visible for this Activity
        splashScreen.setKeepOnScreenCondition { true }
        if(User.getToken(this) == null){
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            startActivity(Intent(this, MissionActivity::class.java))
        }
        finish()
    }
}