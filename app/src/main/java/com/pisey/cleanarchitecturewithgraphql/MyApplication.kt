package com.pisey.cleanarchitecturewithgraphql

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication

class MyApplication: MultiDexApplication() {
    companion object {
        var appContext: Context? = null
            private set
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        base?.let { appContext = it }

    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}