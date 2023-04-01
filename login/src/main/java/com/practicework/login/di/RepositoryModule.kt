package com.practicework.login.di

import com.practicework.login.data.source.LoginRepositoryImpl
import com.practicework.login.domain.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(loginRepositoryImpl: LoginRepositoryImpl) : LoginRepository
}