package com.chivers.kotlinwanandroid.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       val requestTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        return response
    }
}