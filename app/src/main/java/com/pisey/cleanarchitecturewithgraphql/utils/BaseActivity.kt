package com.pisey.cleanarchitecturewithgraphql.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.pisey.cleanarchitecturewithgraphql.data.data_sources.remote.CustomResult
import com.pisey.cleanarchitecturewithgraphql.presentation.components.AppDialog
import com.pisey.cleanarchitecturewithgraphql.presentation.components.CustomStateView

abstract class BaseActivity<B : ViewBinding,VM:ViewModel>(val bindingFactory: (LayoutInflater) -> B, private val mViewModelClass: Class<VM>) : AppCompatActivity() {

    lateinit var binding: B
    val viewModel by lazy {
        ViewModelProvider(this)[mViewModelClass]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
    }

    fun showLoading(){
        AppDialog.showDialogLoading(this,"Loading")
    }
    fun hideLoading(){
        AppDialog.dismissDialogLoading()
    }

    fun showDialogError(message:String){
        AppDialog.showDialogRequestError(this,message)
    }
    fun showDialogInfo(message: String){
        AppDialog.showDialogInfo(this,message)
    }
    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun <T : Any> MutableLiveData<CustomResult<T>>.validateResultToAny(onSuccess:(T)->Unit){
        this.observe(this@BaseActivity){
            when(it){
                is CustomResult.Loading -> showLoading()
                is CustomResult.Failed -> showDialogError(it.error.message)
                is CustomResult.SomethingWentWrong -> showDialogError(it.error.message!!)
                is CustomResult.Success -> {
                    hideLoading()
                    onSuccess(it.data)
                }
            }
        }

    }

    fun <T : Any> MutableLiveData<CustomResult<T>>.validateResultToStateView(stateView: CustomStateView, onSuccess:(T)->Unit){
        this.observe(this@BaseActivity){
            when(it){
                is CustomResult.Loading -> Unit
                is CustomResult.Failed -> stateView.showFailed()
                is CustomResult.SomethingWentWrong -> stateView.showSomethingWentWrong()
                is CustomResult.Success -> {
                    onSuccess(it.data)
                    stateView.showSuccess()
                }
            }
        }

    }

}