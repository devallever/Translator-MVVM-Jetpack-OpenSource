package org.xm.app.translator.opensource.mvvm.repository.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://translate.google.cn/"
    private val mRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(apiServiceClass: Class<T>): T = mRetrofit.create(apiServiceClass)
}