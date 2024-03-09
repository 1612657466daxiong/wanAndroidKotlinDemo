package com.chivers.kotlinwanandroid.network.calladapter.async

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AsyncResponseFlowCallAdapter<R>(private val responseBodyType:R):CallAdapter<R,Flow<Response<R>>> {

    override fun responseType(): Type = responseBodyType as Type

    override fun adapt(call: Call<R>): Flow<Response<R>> = flow {
        suspendCancellableCoroutine<Response<R>> {
            continuation
            -> continuation.invokeOnCancellation {
                call.cancel()
        }
            call.enqueue(object :Callback<R>{
                override fun onResponse(call: Call<R>, response: Response<R>) {
                    continuation.resume(response)
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