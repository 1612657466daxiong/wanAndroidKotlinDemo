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


    fun getBannerModel(){
         homeRepository1.failed
         homeRepository1.getBanner()
    }

    fun getArticleModel(pageIndex:Int){
         homeRepository1.failedArticle
        homeRepository1.getArticles(pageIndex)

    }
}