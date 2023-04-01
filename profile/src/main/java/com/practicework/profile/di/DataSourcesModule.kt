package com.practicework.profile.di

import com.practicework.profile.data.local_data_source.ProfileLocalDataSource
import com.practicework.profile.data.local_data_source.ProfileLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: ProfileLocalDataSourceImpl) : ProfileLocalDataSource
}