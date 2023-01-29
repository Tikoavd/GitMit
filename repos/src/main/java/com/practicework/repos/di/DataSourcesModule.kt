package com.practicework.repos.di

import com.practicework.repos.data.local_data_source.LocalDataSource
import com.practicework.repos.data.local_data_source.LocalDataSourceImpl
import com.practicework.repos.data.remote_data_source.source.RemoteDataSource
import com.practicework.repos.data.remote_data_source.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl) : RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl) : LocalDataSource
}