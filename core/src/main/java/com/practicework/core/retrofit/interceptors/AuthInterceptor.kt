package com.practicework.core.retrofit.interceptors

import android.content.SharedPreferences
import com.practicework.core.di.EMPTY_TOKEN
import com.practicework.core.di.TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sharedPref: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPref.getString(TOKEN, EMPTY_TOKEN) ?: EMPTY_TOKEN
        val request = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}