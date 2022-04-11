package com.pisey.cleanarchitecturewithgraphql.utils

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.pisey.cleanarchitecturewithgraphql.presentation.components.LoadingView

abstract class BaseActivity<B : ViewBinding,VM:ViewModel>(val bindingFactory: (LayoutInflater) -> B, private val mViewModelClass: Class<VM>) : AppCompatActivity() {

    lateinit var binding: B
    val viewModel by lazy {
        ViewModelProvider(this)[mViewModelClass]
    }
    private var loadingView: LoadingView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        onInitialize()
        onEventClick()
        onEventViewModel()
        onEventOther()
        onReady()
    }

    abstract fun onInitialize()
    abstract fun onEventClick()
    abstract fun onEventViewModel()
    abstract fun onEventOther()
    abstract fun onReady()

    open fun showLoading() {
        loadingView = loadingView ?: LoadingView(this).show(this, null)
    }

    open fun hideLoading() {
        loadingView?.let {
            it.removeAllViews()
            loadingView = null
        }
    }
}