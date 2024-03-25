package com.chivers.kotlinwanandroid.model

data class BannerResponse (
    val data:List<Banner>,
    override val errorMsg:String,
    override val errorCode: Int,
):BaseResponse(errorCode,errorMsg)

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