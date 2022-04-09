package com.pisey.cleanarchitecturewithgraphql.presentation.components

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.pisey.cleanarchitecturewithgraphql.R

class LoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    fun show(activity: Activity, parent: ViewGroup? = null): LoadingView {

        val layoutParams = MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.setMargins(0, 0, 0, 0)
        parent?.addView(this, layoutParams) ?: run {
            activity.window.decorView
                    .findViewById<ViewGroup>(android.R.id.content)
                    .addView(this, layoutParams)
        }
        return this
    }
    init {
        LayoutInflater.from(context).inflate(
            R.layout.view_loading, this)
    }
}