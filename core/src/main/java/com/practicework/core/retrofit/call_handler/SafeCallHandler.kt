package com.practicework.core.retrofit.call_handler

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.UnknownHostException

const val NO_INTERNET_ACCESS = "Can not connect to network"

inline fun <RESPONSE, RESULT> safeApiCall(
    crossinline mapper: (RESPONSE) -> RESULT,
    crossinline body: suspend () -> Response<RESPONSE>
): Flow<Resource<RESULT>> {
    return flow {
        emit(Resource.Loading)
        try {
            val response = body.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(mapper(it)))
                }
            } else {
                val exception = Exception("Invalid Token")
                emit(Resource.Error(exception, null))
            }
        } catch (t: Throwable) {
            val exception = when (t) {
                is UnknownHostException -> {
                    Exception(NO_INTERNET_ACCESS)
                }
                else -> {
                    Exception("Could not fetch from network")
                }
            }
            emit(Resource.Error(exception = exception, null))
        }
    }
}