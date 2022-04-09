package com.pisey.cleanarchitecturewithgraphql

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.pisey.cleanarchitecturewithgraphql.utils.ContextApplication

class MyApplication: MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        base?.let { ContextApplication.context = it }

    }

}