package com.chivers.kotlinwanandroid.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chivers.kotlinwanandroid.model.Article
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

    lateinit var banner: MutableLiveData<BannerResponse>

    lateinit var articles: MutableLiveData<List<Article>>
    lateinit var failedArticles:LiveData<String>

    fun getBannerModel(){
        failed = homeRepository1.failed
        banner = homeRepository1.getBanner()
    }

    fun getArticleModel(pageIndex:Int){
        failedArticles = homeRepository1.failedArticle
        articles = homeRepository1.getArticles(pageIndex)
        Log.i("articles",articles.toString())
    }
}