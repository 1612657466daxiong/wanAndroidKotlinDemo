package com.chivers.kotlinwanandroid.model

data class UserInfoResponse (
    val errorCode: Int,
    val errorMsg: String,
    val data:UserWarper,
)

data class UserWarper(
    val coinInfo:CoinInfo,
    val userInfo: UserInfo
)

data class CoinInfo(
    val coinCount:Int,
    val level:Int,
    val nickname:String,
    val username:String,
    val userId:Int
)
data class UserInfo(
    val admin: Boolean,
    val coinCount: Int,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)