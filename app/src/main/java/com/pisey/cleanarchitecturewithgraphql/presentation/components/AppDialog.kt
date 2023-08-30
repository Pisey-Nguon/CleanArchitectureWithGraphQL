package com.pisey.cleanarchitecturewithgraphql.presentation.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pisey.cleanarchitecturewithgraphql.R
import com.pisey.cleanarchitecturewithgraphql.databinding.LayoutLoadingDialogBinding

object AppDialog {

    private var loadingDialog:ArrayList<androidx.appcompat.app.AlertDialog> = ArrayList()


    fun showDialogRequestError(context: Context,message: String?){
        dismissDialogLoading()
        MaterialAlertDialogBuilder(context)
            .setMessage(message)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    fun showDialogRequestError(context: Context, @StringRes message: Int){
        dismissDialogLoading()
        MaterialAlertDialogBuilder(context)
            .setMessage(message)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    fun showDialogInfo(context: Context,message: String?) {
        dismissDialogLoading()
        MaterialAlertDialogBuilder(context)
            .setMessage(message)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    fun showDialogInfo(context: Context,@StringRes message: Int) {
        dismissDialogLoading()
        MaterialAlertDialogBuilder(context)
            .setMessage(message)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    fun showDialogInfo(context: Context,title: String, message: String) {
        dismissDialogLoading()
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun showDialogInfo(context: Context,@StringRes title: Int, @StringRes message: Int) {
        dismissDialogLoading()
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun showDialogLoading(context: Context,message: String) {
        val view = LayoutLoadingDialogBinding.inflate(LayoutInflater.from(context))
        view.titleLoading.text = message
        loadingDialog.add(MaterialAlertDialogBuilder(context)
            .setView(view.root)
            .setCancelable(false)
            .create())
        loadingDialog.last().window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
        loadingDialog.last().show()

    }

    fun showDialogLoading(context: Context,@StringRes message: Int) {
        val view = LayoutLoadingDialogBinding.inflate(LayoutInflater.from(context))
        view.titleLoading.text = context.getString(message)
        loadingDialog.add( MaterialAlertDialogBuilder(context)
            .setView(view.root)
            .setCancelable(false)
            .create())
        loadingDialog.last().window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
        loadingDialog.last().show()
    }

    fun dismissDialogLoading(){
        loadingDialog.forEach {
            it.dismiss()
        }
    }



}