package com.practicework.profile.di

import com.practicework.profile.data.source.ProfileRepositoryImpl
import com.practicework.profile.domain.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(profileRepositoryImpl: ProfileRepositoryImpl) : ProfileRepository
}