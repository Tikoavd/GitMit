package com.practicework.repos.di

import com.practicework.core.di.RetrofitAuthRx
import com.practicework.repos.data.remote_data_source.retrofit.ReposApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    fun provideReposApiService(@RetrofitAuthRx retrofit: Retrofit) : ReposApiService =
            retrofit.create(ReposApiService::class.java)

}