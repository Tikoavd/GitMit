package com.practicework.repos.di

import com.practicework.repos.data.local_data_source.ReposLocalDataSource
import com.practicework.repos.data.local_data_source.ReposLocalDataSourceImpl
import com.practicework.repos.data.remote_data_source.source.ReposRemoteDataSource
import com.practicework.repos.data.remote_data_source.source.ReposRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: ReposRemoteDataSourceImpl) : ReposRemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: ReposLocalDataSourceImpl) : ReposLocalDataSource
}