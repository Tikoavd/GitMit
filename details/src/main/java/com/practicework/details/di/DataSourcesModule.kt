package com.practicework.details.di

import com.practicework.details.data.remote_data_source.source.RemoteDataSource
import com.practicework.details.data.remote_data_source.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl) : RemoteDataSource
}