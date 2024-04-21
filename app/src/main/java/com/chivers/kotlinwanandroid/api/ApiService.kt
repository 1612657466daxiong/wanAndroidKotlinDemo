package com.chivers.kotlinwanandroid.api

import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.chivers.kotlinwanandroid.model.ArticlesResponse
import com.chivers.kotlinwanandroid.model.BannerResponse
import com.chivers.kotlinwanandroid.model.UserInfoResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("/banner/json")
    fun getBanner():Flow<BannerResponse>

    @GET("/user/lg/userinfo/json")
    fun getUserInfoCall():Flow<UserInfoResponse>

    @GET("/article/list/{page_index}/json")
    fun getArticlesCall(@Path("page_index") pageIndex:Int):Flow<ArticlesResponse>
}