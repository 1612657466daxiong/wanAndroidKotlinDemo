package com.chivers.kotlinwanandroid.api

import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.chivers.kotlinwanandroid.model.BannerResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET


interface ApiService {
    @GET("/banner/json")
    fun getBanner():Flow<BannerResponse>
}