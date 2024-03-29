package com.chivers.kotlinwanandroid.network

import com.chivers.kotlinwanandroid.network.calladapter.FlowCallAdapterFactory
import com.chivers.kotlinwanandroid.network.interceptor.RequestInterceptor
import com.chivers.kotlinwanandroid.network.interceptor.ResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetWorkApi  {
    const val BASE_URL:String = "https://www.wanandroid.com"
    /**
     * 版本信息 用于日志打印
     * */
    private lateinit var netWorkInfo:INetWorkInfo
    private var okHttpClient: OkHttpClient? = null
    private val retrofitHashMap = HashMap<String,Retrofit>()

    public fun init(inetworkInfo:INetWorkInfo){
        netWorkInfo = inetworkInfo
    }

    fun <T> createService(serviceClass:Class<T>): T {
        return getRetrofit(serviceClass).create(serviceClass)
    }

    fun getOkhttpClient():OkHttpClient{
        if(okHttpClient==null){
            val  builder:OkHttpClient.Builder = OkHttpClient.Builder()
            val cacheSize = 100*1024*1024
            builder.connectTimeout(6,TimeUnit.SECONDS)
                .addInterceptor(RequestInterceptor(netWorkInfo))
                .addInterceptor(ResponseInterceptor())

            if(netWorkInfo.isDebug()){
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                builder.addInterceptor(httpLoggingInterceptor)
            }
            okHttpClient = builder.build()
        }
        return okHttpClient!!
    }

    fun <T> getRetrofit(serviceClass:Class<T>): Retrofit {
        if(retrofitHashMap[BASE_URL+serviceClass.name] !=null){
            return retrofitHashMap[BASE_URL+serviceClass.name]!!
        }
        val builder = Retrofit.Builder()
        builder.baseUrl(BASE_URL)
            .client(getOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create())

        val retrofit = builder.build()
        retrofitHashMap.put(BASE_URL+serviceClass.name,retrofit)
        return retrofit
    }

}