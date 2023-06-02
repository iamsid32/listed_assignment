package com.example.listedpoc.screens.activity

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listedpoc.R
import com.example.listedpoc.model_factory.BaseViewModelFactory

abstract class BaseActivity : AppCompatActivity(){

    companion object{
        var TAG:String = BaseActivity::class.java.simpleName
    }

    private var toastMessage: Toast? = null

    private var loaderAlertDialog: AlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun showBlockingLoader(){
        showBlockingLoader(true)
    }

    fun showBlockingLoader(dismissOnTouchOutSide:Boolean){
        runOnUiThread {
            if(loaderAlertDialog==null){
                initBlockingLoaderDialog()
            }
            loaderAlertDialog?.setCanceledOnTouchOutside(dismissOnTouchOutSide)
            loaderAlertDialog?.show()
        }
    }


    fun hideBlockingLoader(){
        runOnUiThread {
            if(loaderAlertDialog?.isShowing==true){
                loaderAlertDialog?.dismiss()
            }
            //loaderAlertDialog = null
        }
    }

    fun showToast(message:String?){
        if (message != null) {
            cancelToastMessage()
            if (applicationContext != null) {
                toastMessage = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
                toastMessage?.show()
            }
        }
    }

    override fun onDestroy() {
        loaderAlertDialog?.dismiss()
        loaderAlertDialog = null
        super.onDestroy()
    }

    private fun cancelToastMessage() {
        toastMessage?.cancel()
    }

    private fun initBlockingLoaderDialog(){
        loaderAlertDialog = AlertDialog.Builder(this@BaseActivity).apply {
            setView(LayoutInflater.from(context).inflate(R.layout.layout_blocking_loader, null))
            setCancelable(true)
        }.create()

        loaderAlertDialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    protected fun<T: ViewModel> getViewModel(activity: AppCompatActivity, viewModel: ViewModel, className:Class<T>): T {
        return ViewModelProvider(activity, BaseViewModelFactory(viewModel,className)).get(className)
    }

    override fun finish() {
        super.finish()
    }

}