package com.practicework.login.di

import com.practicework.login.data.local_data_source.LocalDataSource
import com.practicework.login.data.local_data_source.LocalDataSourceImpl
import com.practicework.login.data.remote_data_source.source.RemoteDataSource
import com.practicework.login.data.remote_data_source.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl)
            : RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl)
            : LocalDataSource
}