package com.chivers.kotlinwanandroid.network.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    const val STANDARD_TIME = "yyyy-MM-dd HH:mm:ss"
    fun getDateTime():String{
        return SimpleDateFormat(STANDARD_TIME, Locale.CHINESE).format(Date())
    }

}