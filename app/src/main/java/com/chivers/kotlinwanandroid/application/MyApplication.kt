package com.chivers.kotlinwanandroid.application

import android.app.Application
import com.chivers.kotlinwanandroid.network.INetWorkInfo
import com.chivers.kotlinwanandroid.network.NetWorkApi
import com.chivers.kotlinwanandroid.network.NetWorkRequiredInfo

class MyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        NetWorkApi.init(NetWorkRequiredInfo(this))
    }
}