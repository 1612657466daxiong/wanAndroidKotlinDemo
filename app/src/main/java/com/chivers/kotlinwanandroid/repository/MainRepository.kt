package com.chivers.kotlinwanandroid.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chivers.kotlinwanandroid.api.ApiService
import com.chivers.kotlinwanandroid.model.BannerResponse
import com.chivers.kotlinwanandroid.model.UserInfoResponse
import com.chivers.kotlinwanandroid.network.NetWorkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainRepository:BaseRepository() {
    val userInfo:MutableLiveData<UserInfoResponse>  = MutableLiveData<UserInfoResponse>()
    val failed:MutableLiveData<String> = MutableLiveData()
    fun getUserInfoRequest():MutableLiveData<UserInfoResponse>{
        MainScope().launch {
            NetWorkApi.createService(ApiService::class.java)?.getUserInfoCall()
                ?.flowOn(Dispatchers.IO)
                ?.catch {
                    Log.e("xqw","rrrrr"+it?.message)
                    failed.postValue(it?.message)
                }?.map { value -> value  }?.flowOn(Dispatchers.Main)?.collect(){
                    Log.e("xqw","rrrrr qingqiu"+Thread.currentThread().name)
                    userInfo.value = it
                }
        }
        return  userInfo
    }
}