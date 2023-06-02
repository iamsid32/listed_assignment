package com.example.listedpoc

import android.app.Application
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.webkit.WebView
import com.example.listedpoc.api.BaseCloudAPIService
import retrofit2.Retrofit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppController: Application() {

    companion object{
        val TAG:String = AppController::class.java.simpleName
        var appController:AppController? = null
        var cloudApiService: Retrofit? = null
        var ioExecutor: ExecutorService? = null
    }

    override fun onCreate() {
        super.onCreate()
        appController = this
        ioExecutor = Executors.newSingleThreadExecutor()
        cloudApiService = BaseCloudAPIService()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG, "onConfigurationChanged")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d(TAG, "onLowMemory")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.d(TAG, "onTrimMemory")
    }

    override fun onTerminate() {
        Log.d(TAG, "onTerminate")
        super.onTerminate()
    }

}