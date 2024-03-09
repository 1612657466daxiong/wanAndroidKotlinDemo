package com.chivers.kotlinwanandroid.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chivers.kotlinwanandroid.api.ApiService
import com.chivers.kotlinwanandroid.model.BannerResponse
import com.chivers.kotlinwanandroid.network.NetWorkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeRepository:BaseRepository() {
    val bannerImage:MutableLiveData<BannerResponse>  = MutableLiveData<BannerResponse>()
    init {

    }

    val failed:MutableLiveData<String> = MutableLiveData()


    fun getBanner(): MutableLiveData<BannerResponse> {
        MainScope().launch {
            NetWorkApi.createService(ApiService::class.java)?.getBanner()
                ?.flowOn(Dispatchers.IO)
                ?.catch {
                Log.e("xqw","rrrrr"+it?.message)
                failed.postValue(it?.message)
            }?.flowOn(Dispatchers.Main)?.collect(){
                Log.e("xqw","rrrrr qingqiu"+Thread.currentThread().name)
               bannerImage.value = it
            }
        }
        return bannerImage
    }
}