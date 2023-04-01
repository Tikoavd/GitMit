package com.practicework.all_users.di

import com.practicework.all_users.data.local_data_source.AllUsersLocalDataSource
import com.practicework.all_users.data.local_data_source.AllUsersLocalDataSourceImpl
import com.practicework.all_users.data.remote_data_source.source.AllUsersRemoteDataSource
import com.practicework.all_users.data.remote_data_source.source.AllUsersRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: AllUsersRemoteDataSourceImpl) : AllUsersRemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: AllUsersLocalDataSourceImpl) : AllUsersLocalDataSource
}