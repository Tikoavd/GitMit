package com.practicework.login.di

import com.practicework.login.data.local_data_source.LoginLocalDataSource
import com.practicework.login.data.local_data_source.LoginLocalDataSourceImpl
import com.practicework.login.data.remote_data_source.source.LoginRemoteDataSource
import com.practicework.login.data.remote_data_source.source.LoginRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: LoginRemoteDataSourceImpl)
            : LoginRemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LoginLocalDataSourceImpl)
            : LoginLocalDataSource
}