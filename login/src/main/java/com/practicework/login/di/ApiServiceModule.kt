package com.practicework.login.di

import com.practicework.core.di.RetrofitAuth
import com.practicework.login.data.remote_data_source.retrofit.LoginApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    fun provideApiService(@RetrofitAuth retrofit: Retrofit): LoginApiService {
        return retrofit.create(LoginApiService::class.java)
    }
}