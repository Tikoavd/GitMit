package com.practicework.repos.data.source

import com.practicework.core.room.models.RepoEntity
import com.practicework.repos.data.remote_data_source.models.RepoApiModel
import com.practicework.repos.domain.models.Repo

object Mappers {

    fun mapRepoApiModelToRepo(repoApiModel: RepoApiModel): Repo =
        Repo(
            name = repoApiModel.name ?: Repo.UNKNOWN_FIELD_VALUE,
            language = repoApiModel.language ?: "unknown",
            visibility = repoApiModel.visibility ?: Repo.UNKNOWN_FIELD_VALUE
        )

    fun mapRepoApiModelListToRepoList(repoApiModelList: List<RepoApiModel>) : List<Repo> =
        repoApiModelList.map { mapRepoApiModelToRepo(it) }

    fun mapRepoEntityToRepo(repoEntity: RepoEntity) : Repo =
        Repo(
            name = repoEntity.name,
            language = repoEntity.language,
            visibility = repoEntity.visibility
        )

    fun mapRepoEntityListToRepoList(repoEntityList: List<RepoEntity>) : List<Repo> =
        repoEntityList.map { mapRepoEntityToRepo(it) }

    fun mapRepoToRepoEntity(repo: Repo) : RepoEntity =
        RepoEntity(
            name = repo.name,
            language = repo.language,
            visibility = repo.visibility
        )

    fun mapRepoListToRepoEntityList(repoList: List<Repo>) : List<RepoEntity> =
        repoList.map { mapRepoToRepoEntity(it) }
}