package com.chivers.kotlinwanandroid.network.calladapter.sync

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ResponseFlowCallAdapter<R>(private val responseBodyType:R) :CallAdapter<R, Flow<Response<R>>>{
    override fun responseType(): Type= responseBodyType as Type

    override fun adapt(call: Call<R>): Flow<Response<R>> = flow {
        suspendCancellableCoroutine {
            cancellableContinuation ->
            cancellableContinuation.invokeOnCancellation {
                call.cancel()
            }
            try {
                val response = call.execute()
                cancellableContinuation.resume(response)
            }catch (e:java.lang.Exception){
                cancellableContinuation.resumeWithException(e)
            }
        }.let {
            emit(it)
        }
    }.take(1)

}