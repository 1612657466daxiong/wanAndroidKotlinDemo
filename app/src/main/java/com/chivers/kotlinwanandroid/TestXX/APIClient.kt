package com.chivers.kotlinwanandroid.TestXX

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {
    private object Holder{
        val INSTANCE = APIClient()
    }
    companion object{
        val instance = Holder.INSTANCE
    }

    fun <T> instanceRetrofit(apiInterface:Class<T>):T{
        val mOkHttpClient= OkHttpClient().newBuilder().apply {
            readTimeout(30,TimeUnit.SECONDS)
            connectTimeout(30,TimeUnit.SECONDS)
            writeTimeout(30,TimeUnit.SECONDS)
        }.build()
        val retrofit:Retrofit = Retrofit.Builder().baseUrl("https://www.wanandroid.com")
            .client(mOkHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return  retrofit.create(apiInterface)
    }
}