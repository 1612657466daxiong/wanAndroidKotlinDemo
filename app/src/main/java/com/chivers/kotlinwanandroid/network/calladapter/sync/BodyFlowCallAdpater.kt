package com.chivers.kotlinwanandroid.network.calladapter.sync

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import java.lang.reflect.Type
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BodyFlowCallAdapter<R>(private val responseBodyType:R) :CallAdapter<R, Flow<R>>{
    override fun responseType()=responseBodyType as Type

    override fun adapt(call: Call<R>): Flow<R> =bodyFlow(call)
}

fun <R> bodyFlow(call:Call<R>):Flow<R> = flow {
    suspendCancellableCoroutine<R> {
            cancellableContinuation ->
        cancellableContinuation.invokeOnCancellation {
            call.cancel()
        }
        try {
            val response = call.execute()
            if(response.isSuccessful){
                cancellableContinuation.resume(response.body()!!)
            }else{
                cancellableContinuation.resumeWithException(HttpException(response))
            }
        }   catch (e:java.lang.Exception){
            cancellableContinuation.resumeWithException(e)
        }
    }.let {
        emit(it)
    }
}.take(1)