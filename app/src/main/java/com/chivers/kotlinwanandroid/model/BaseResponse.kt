package com.chivers.kotlinwanandroid.model

abstract class BaseResponse (
    open val errorCode:Int,
    open val errorMsg:String
)