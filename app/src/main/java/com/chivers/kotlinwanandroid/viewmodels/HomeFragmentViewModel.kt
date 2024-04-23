package com.chivers.kotlinwanandroid.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import com.chivers.kotlinwanandroid.model.ArticlesResponse
import com.chivers.kotlinwanandroid.model.BannerResponse
import com.chivers.kotlinwanandroid.repository.HomeFragmentRepository
import com.chivers.kotlinwanandroid.repository.HomeRepository

class HomeFragmentViewModel public constructor( homeRepository:HomeFragmentRepository)  :BaseViewModel(){
    lateinit var homeRepository1: HomeFragmentRepository
    init {
        homeRepository1 = homeRepository
    }
    lateinit var failed: LiveData<String>

    lateinit var banner: LiveData<BannerResponse>

    lateinit var articles: LiveData<ArticlesResponse>
    lateinit var failedArticles:LiveData<String>

    fun getBannerModel(){
        failed = homeRepository1.failed
        banner = homeRepository1.getBanner()
    }

    fun getArticleModel(){
        failedArticles = homeRepository1.failedArticle
        articles = homeRepository1.getArticles()
        Log.i("articles",articles.value?.data?.datas.toString())
    }
}