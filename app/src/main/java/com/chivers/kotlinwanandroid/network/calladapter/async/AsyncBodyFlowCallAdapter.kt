package com.chivers.kotlinwanandroid.network.calladapter.async

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AsyncBodyFlowCallAdapter<R>(private val  responseBodyType:R):CallAdapter<R, Flow<R>> {
    override fun responseType(): Type = responseBodyType as Type

    override fun adapt(call: Call<R>): Flow<R> = flow{
        suspendCancellableCoroutine<R> {
            continuation -> continuation.invokeOnCancellation {
                call.cancel()
            }
            call.enqueue(object : Callback<R>{
                override fun onResponse(call: Call<R>, response: Response<R>) {
                    if(response.isSuccessful){
                        continuation.resume(response.body()!!)
                    }else{
                        continuation.resumeWithException(HttpException(response))
                    }

                }

                override fun onFailure(call: Call<R>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }.let {
            emit(it)
        }
    }.take(1)

}