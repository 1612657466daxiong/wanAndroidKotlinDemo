package com.chivers.kotlinwanandroid.db


import com.chivers.kotlinwanandroid.utils.Constant
import com.tencent.mmkv.MMKV

class MMKVHelper private constructor(){


    fun isLogin():Boolean{
       val mmkv = MMKV.defaultMMKV()
        if(mmkv.containsKey(Constant.MMKV_IS_LOGIN)){
            return mmkv.getBoolean(Constant.MMKV_IS_LOGIN,false)
        }
        return false
    }

    companion object {
        val instance:MMKVHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            MMKVHelper()
        }
    }
}