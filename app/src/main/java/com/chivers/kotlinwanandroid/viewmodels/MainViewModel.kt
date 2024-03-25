package com.chivers.kotlinwanandroid.viewmodels

import androidx.lifecycle.LiveData
import com.chivers.kotlinwanandroid.model.BannerResponse
import com.chivers.kotlinwanandroid.model.UserInfoResponse
import com.chivers.kotlinwanandroid.repository.HomeRepository
import com.chivers.kotlinwanandroid.repository.MainRepository

class MainViewModel public constructor(mainViewModelParam :MainRepository) :BaseViewModel(){
    lateinit var mainViewModel: MainRepository
    init {
        mainViewModel = mainViewModelParam
    }
    lateinit var failed: LiveData<String>

    lateinit var userInfoWrapper: LiveData<UserInfoResponse>

    fun getBannerModel(){
        failed = mainViewModel.failed
        userInfoWrapper = mainViewModel.getUserInfoRequest()
    }
}