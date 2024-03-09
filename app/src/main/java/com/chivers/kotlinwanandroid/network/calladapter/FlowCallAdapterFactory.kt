package com.chivers.kotlinwanandroid.network.calladapter

import com.chivers.kotlinwanandroid.network.calladapter.async.AsyncBodyFlowCallAdapter
import com.chivers.kotlinwanandroid.network.calladapter.async.AsyncResponseFlowCallAdapter
import com.chivers.kotlinwanandroid.network.calladapter.sync.BodyFlowCallAdapter
import com.chivers.kotlinwanandroid.network.calladapter.sync.ResponseFlowCallAdapter
import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory private constructor(private val async:Boolean): CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if(getRawType(returnType)!= Flow::class.java) return null
        if(returnType !is ParameterizedType){
            throw IllegalStateException("the flow type must be parameterized as flow<Foo>!")
        }
        val flowableType = getParameterUpperBound(0,returnType)
        val rawFlowableType = getRawType(flowableType)
        return  if(rawFlowableType == Response::class.java){
            if(flowableType !is ParameterizedType){
                throw  IllegalStateException("the respone type must be parameterized as Response<Foo>!")
            }
            val responeseBodyType = getParameterUpperBound(0,flowableType)
            createResponseFlowCallAdapter(async,responeseBodyType)
        }else{
            createBodyFlowCallAdapter(async,flowableType)
        }
    }
    companion object{
        @JvmStatic
        fun create(async: Boolean= false)= FlowCallAdapterFactory(async)
    }
}

private fun createResponseFlowCallAdapter(async: Boolean,responseBodyType:Type)=
    if (async)
        AsyncResponseFlowCallAdapter(responseBodyType)
    else
        ResponseFlowCallAdapter(responseBodyType)

private fun  createBodyFlowCallAdapter(async: Boolean,responseBodyType: Type)=
    if (async)
        AsyncBodyFlowCallAdapter(responseBodyType)
    else
        BodyFlowCallAdapter(responseBodyType)