package com.practicework.repos.di

import com.practicework.core.mappers.ListMapper
import com.practicework.core.mappers.ListMapperImpl
import com.practicework.core.mappers.Mapper
import com.practicework.core.room.models.RepoEntity
import com.practicework.repos.data.remote_data_source.models.RepoApiModel
import com.practicework.repos.data.source.mappers.RepoApiModelMapper
import com.practicework.repos.data.source.mappers.RepoEntityMapper
import com.practicework.repos.data.source.mappers.RepoToEntityMapper
import com.practicework.repos.domain.models.Repo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindRepoApiModelMapper(impl: RepoApiModelMapper) : Mapper<RepoApiModel, Repo>

    @Binds
    abstract fun bindRepoEntityMapper(impl: RepoEntityMapper) : Mapper<RepoEntity, Repo>

    @Binds
    abstract fun bindRepoToEntityMapper(impl: RepoToEntityMapper) : Mapper<Repo, RepoEntity>
}

@Module
@InstallIn(SingletonComponent::class)
object MapperProvidingModule {

    @Provides
    fun provideRepoApiModelListMapper(mapper: Mapper<RepoApiModel, Repo>)
                : ListMapper<RepoApiModel, Repo> = ListMapperImpl(mapper)

    @Provides
    fun provideRepoEntityListMapper(mapper: Mapper<RepoEntity, Repo>)
                : ListMapper<RepoEntity, Repo> = ListMapperImpl(mapper)

    @Provides
    fun provideRepoToEntityListMapper(mapper: Mapper<Repo, RepoEntity>)
            : ListMapper<Repo, RepoEntity> = ListMapperImpl(mapper)
}