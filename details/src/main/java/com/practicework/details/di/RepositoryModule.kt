package com.practicework.details.di

import com.practicework.details.data.source.DetailsRepositoryImpl
import com.practicework.details.domain.DetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(detailsRepositoryImpl: DetailsRepositoryImpl) : DetailsRepository
}