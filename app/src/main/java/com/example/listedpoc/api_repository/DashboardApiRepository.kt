package com.example.listedpoc.api_repository

import com.example.listedpoc.api.BaseApiService
import com.example.listedpoc.model.DashboardModel
import com.example.listedpoc.screens.activity.BaseActivity

class DashboardApiRepository(private val baseApiService: BaseApiService,private val baseActivity: BaseActivity?) : BaseRepository(baseActivity,baseApiService) {

    suspend fun getDashboardData(showBlockingLoader:Boolean): DashboardModel? {
        return doSafeAPIRequest(call = { baseApiService.getDashboardData() }, showBlockingLoader = showBlockingLoader)
    }

}