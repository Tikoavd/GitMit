package com.practicework.all_users.di

import com.practicework.all_users.data.source.AllUsersRepositoryImpl
import com.practicework.all_users.domain.AllUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(allUsersRepositoryImpl: AllUsersRepositoryImpl) : AllUsersRepository
}