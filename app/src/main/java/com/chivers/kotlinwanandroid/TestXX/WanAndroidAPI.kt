package com.chivers.kotlinwanandroid.TestXX

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WanAndroidAPI {
    @POST("/user/login")
    @FormUrlEncoded
    suspend fun loginAction(@Field("username") userName:String,@Field("password") password:String):LoginRegisterResponseWrapper<LoginRegisterResponse>
}