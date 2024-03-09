package com.chivers.kotlinwanandroid.network

import android.app.Application
import com.chivers.kotlinwanandroid.BuildConfig

class NetWorkRequiredInfo (private val application: Application):INetWorkInfo {

    override fun getAppVersionName(): String = BuildConfig.VERSION_NAME

    override fun getAppVersionCode(): String =  BuildConfig.VERSION_CODE.toString()

    override fun isDebug(): Boolean = BuildConfig.DEBUG

    override fun getApplicationContext(): Application =application
}