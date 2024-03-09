package com.chivers.kotlinwanandroid.viewmodels

import androidx.lifecycle.LiveData
import com.chivers.kotlinwanandroid.model.BannerResponse
import com.chivers.kotlinwanandroid.repository.BaseRepository
import com.chivers.kotlinwanandroid.repository.HomeRepository

class HomeViewModel public constructor( homeRepository:HomeRepository) : BaseViewModel() {
    lateinit var homeRepository1:HomeRepository
    init {
        homeRepository1 = homeRepository
    }
    lateinit var failed:LiveData<String>

    lateinit var banner:LiveData<BannerResponse>

    fun getBannerModel(){
        failed = homeRepository1.failed
        banner = homeRepository1.getBanner()
    }
}