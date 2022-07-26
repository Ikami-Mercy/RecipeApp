package com.recipeMenu.task2.api

import com.recipeMenu.task2.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//A class to define how network requests are made

class ApiClient {
    fun getApiService(testEnvironment: Boolean): ApiInterface {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor(testEnvironment))
            .build()

        val retrofitBuilder = Retrofit.Builder()
        val baseURl = Constants.BASE_URL
        retrofitBuilder.baseUrl(baseURl)
        retrofitBuilder.client(client)
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
        val retrofit: Retrofit = retrofitBuilder.build()
        return retrofit.create(ApiInterface::class.java)
    }
    private fun loggingInterceptor(testEnvironment: Boolean): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (testEnvironment) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }
}