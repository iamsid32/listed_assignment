package com.example.listedpoc.screens.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.listedpoc.model_factory.BaseViewModelFactory
import com.example.listedpoc.screens.activity.BaseActivity

abstract class BaseFragment :Fragment(){

    companion object{
        var TAG = BaseFragment::class.java.simpleName
    }

    protected val _activity by lazy { context as BaseActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    protected fun showToast(message: String?){
        if(activity is BaseActivity) {
            (activity as BaseActivity).showToast(message)
        }

    }

    protected fun<T:ViewModel> getViewModel(fragment:Fragment,viewModel: ViewModel,className:Class<T>): T {
        return ViewModelProviders.of(fragment, BaseViewModelFactory(viewModel,className)).get(className)
    }


}