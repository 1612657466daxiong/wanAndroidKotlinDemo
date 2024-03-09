package com.chivers.kotlinwanandroid.network

import android.app.Application

interface INetWorkInfo {
    fun getAppVersionName():String
    fun getAppVersionCode():String
    fun isDebug():Boolean
    fun getApplicationContext():Application
}