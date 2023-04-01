package com.practicework.repos.di

import com.practicework.repos.data.source.ReposRepositoryImpl
import com.practicework.repos.domain.ReposRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(reposRepositoryImpl: ReposRepositoryImpl) : ReposRepository
}