package com.practicework.details.di

import com.practicework.core.di.RetrofitAuth
import com.practicework.details.data.remote_data_source.retrofit.DetailsApiService
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
    fun provideApiService(@RetrofitAuth retrofit: Retrofit) : DetailsApiService {
        return retrofit.create(DetailsApiService::class.java)
    }
}