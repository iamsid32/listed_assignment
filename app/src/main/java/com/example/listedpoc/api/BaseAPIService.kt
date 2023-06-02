package com.example.listedpoc.api

import com.example.listedpoc.constants.BaseAPIConstants
import com.example.listedpoc.model.DashboardModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface BaseApiService {
    @Headers(BaseAPIConstants.CONTENT_TYPE_JSON)
    @GET(BaseAPIConstants.API_DASHBOARD_NEW)
    suspend fun getDashboardData(): Response<DashboardModel>
}