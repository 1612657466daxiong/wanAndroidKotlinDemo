package com.chivers.kotlinwanandroid.network.interceptor


import com.chivers.kotlinwanandroid.network.INetWorkInfo
import com.chivers.kotlinwanandroid.network.utils.DateUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestInterceptor constructor(inetinfo:INetWorkInfo):Interceptor {
    lateinit var iNetWorkInfo:INetWorkInfo

    init {
        iNetWorkInfo = inetinfo
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val nowDateTime = DateUtil.getDateTime()
        val builder= chain.request().newBuilder()
        builder.addHeader("os","android")
        builder.addHeader("appVersionCode",iNetWorkInfo.getAppVersionCode())
        builder.addHeader("appVersionName",iNetWorkInfo.getAppVersionName())
        builder.addHeader("datetime",nowDateTime)
        return chain.proceed(builder.build())
    }
}