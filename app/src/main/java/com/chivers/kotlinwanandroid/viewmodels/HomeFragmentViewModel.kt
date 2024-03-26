package com.chivers.kotlinwanandroid.viewmodels

import androidx.lifecycle.LiveData
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

    fun getBannerModel(){
        failed = homeRepository1.failed
        banner = homeRepository1.getBanner()
    }
}