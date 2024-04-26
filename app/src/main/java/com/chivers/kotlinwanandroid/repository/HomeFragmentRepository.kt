package com.chivers.kotlinwanandroid.repository

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import com.chivers.kotlinwanandroid.api.ApiService
import com.chivers.kotlinwanandroid.model.Article
import com.chivers.kotlinwanandroid.model.ArticlesResponse
import com.chivers.kotlinwanandroid.model.BannerResponse
import com.chivers.kotlinwanandroid.network.NetWorkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeFragmentRepository :BaseRepository() {
    val bannerImage: MutableLiveData<BannerResponse> = MutableLiveData<BannerResponse>()
    val failed: MutableLiveData<String> = MutableLiveData()
    val articlesList:MutableLiveData<List<Article>> = MutableLiveData<List<Article>>()
    val failedArticle:MutableLiveData<String> = MutableLiveData()


    fun getBanner(): MutableLiveData<BannerResponse> {
        MainScope().launch {
            NetWorkApi.createService(ApiService::class.java).getBanner()
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.e("xqw","rrrrr"+it.message)
                    failed.postValue(it.message)
                }.map { value -> value  }.flowOn(Dispatchers.Main).collect(){
                    Log.e("xqw","rrrrr qingqiu"+Thread.currentThread().name)
                    bannerImage.value = it
                }
        }
        return bannerImage
    }

    fun getArticles(pageIndex:Int):MutableLiveData<List<Article>> {
        MainScope().launch {
            NetWorkApi.createService(ApiService::class.java)
                .getArticlesCall(pageIndex).flowOn(Dispatchers.IO).catch {
                    Log.e("xqw","rrrrr"+it.message)
                    failedArticle.postValue(it.message)
                }.map { value -> value }.flowOn(Dispatchers.Main).collect() {
                    if(pageIndex>1){
                        articlesList.value?.plus(it.data.datas)

                    }else{
                        articlesList.value = it.data.datas
                    }

                }
        }
        return articlesList
    }


}