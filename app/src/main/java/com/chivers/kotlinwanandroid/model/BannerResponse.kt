package com.chivers.kotlinwanandroid.model

data class BannerResponse (
    val data:List<Banner>,
    val errorMsg:String,
    val errorCode: Int,
)

data class Banner(
    val desc: String,
    val id:Int,
    val imagePath:String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String,
)