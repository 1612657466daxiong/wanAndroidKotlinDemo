package com.chivers.kotlinwanandroid.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
       val requestTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        Log.i(TAG,"requestionTime = " + (System.currentTimeMillis()-requestTime) +" ms")
        Log.i(TAG,"Resutl = "+response.body)
        return response
    }

    companion object{
        const val TAG ="ResponseInterceptor"
    }
}