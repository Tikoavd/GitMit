package com.practicework.all_users.di

import com.practicework.all_users.data.local_data_source.LocalDataSource
import com.practicework.all_users.data.local_data_source.LocalDataSourceImpl
import com.practicework.all_users.data.remote_data_source.source.RemoteDataSource
import com.practicework.all_users.data.remote_data_source.source.RemoteDataSourceImpl
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