package com.pisey.cleanarchitecturewithgraphql

import android.content.Context
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

}