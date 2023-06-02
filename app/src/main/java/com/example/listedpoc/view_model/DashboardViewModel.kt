package com.example.listedpoc.view_model

import androidx.lifecycle.MutableLiveData
import com.example.listedpoc.api.BaseApiService
import com.example.listedpoc.api.BaseCloudAPIService
import com.example.listedpoc.api_repository.DashboardApiRepository
import com.example.listedpoc.model.DashboardModel
import com.example.listedpoc.screens.activity.BaseActivity
import kotlinx.coroutines.launch

class DashboardViewModel(baseActivity: BaseActivity?) : BaseViewModel() {

    private val dashboardApiRepository = DashboardApiRepository(BaseCloudAPIService.getApiService(BaseApiService::class.java),baseActivity)

    val dashBoardResponseLivedata = MutableLiveData<DashboardModel>()
    val errorDashboardResponseLiveData = MutableLiveData<String>()

    fun getDashboardData(showBlockingLoader:Boolean){
        scope.launch {
            try {
                dashBoardResponseLivedata.postValue(dashboardApiRepository.getDashboardData(showBlockingLoader))
            } catch (e: Throwable){
                e.printStackTrace()
                errorDashboardResponseLiveData.postValue(e.message)
            }
        }
    }

}