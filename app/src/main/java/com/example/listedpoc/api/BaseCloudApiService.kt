package com.example.listedpoc.api

import com.example.listedpoc.AppController
import com.example.listedpoc.constants.BaseAPIConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


interface BaseCloudAPIService {

    companion object{
        operator fun invoke(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BaseAPIConstants.KEY_BASE_URL)
                .client(OkHttpClient.Builder()
                    .addInterceptor(Interceptor {
                        when (it.request().url.toString()) {
                            BaseAPIConstants.KEY_BASE_URL + BaseAPIConstants.API_DASHBOARD_NEW -> {
                                it.proceed(it.request().newBuilder()
                                    .addHeader(BaseAPIConstants.KEY_ACCEPT,"application/json;")
                                    .addHeader(BaseAPIConstants.KEY_AUTHORIZATION, BaseAPIConstants.KEY_BEARER_TOKEN)
                                    .build()
                                )
                            }
                            else -> {
                                it.proceed(it.request().newBuilder()
                                    .addHeader(BaseAPIConstants.KEY_ACCEPT,"application/json;")
                                    .addHeader(BaseAPIConstants.KEY_AUTHORIZATION, BaseAPIConstants.KEY_BEARER_TOKEN)
                                    .build()
                                )
                            }
                        }
                    })
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        fun<T> getApiService(service: Class<T>):T{
            if(AppController.cloudApiService!=null){
                return AppController.cloudApiService!!.create(service)
            }else{
                throw Throwable("CloudApiService cannot be null")
            }
        }

    }

}