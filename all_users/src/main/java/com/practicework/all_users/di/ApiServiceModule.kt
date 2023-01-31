package com.practicework.all_users.di

import com.practicework.all_users.data.remote_data_source.retrofit.AllUsersApiService
import com.practicework.core.di.RetrofitAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    fun provideAllUsersApiService(@RetrofitAuth retrofit: Retrofit) : AllUsersApiService {
        return retrofit.create(AllUsersApiService::class.java)
    }
}