package com.practicework.repos.data.source

import com.practicework.core.room.models.RepoDbModel
import com.practicework.repos.data.remote_data_source.models.RepoApiModel
import com.practicework.repos.domain.models.Repo

object Mappers {

    fun mapRepoApiModelToRepo(repoApiModel: RepoApiModel): Repo =
        Repo(
            name = repoApiModel.name,
            language = repoApiModel.language ?: "unknown",
            visibility = repoApiModel.visibility
        )

    fun mapRepoApiModelListToRepoList(repoApiModelList: List<RepoApiModel>) : List<Repo> =
        repoApiModelList.map { mapRepoApiModelToRepo(it) }

    fun mapRepoDbModelToRepo(repoDbModel: RepoDbModel) : Repo =
        Repo(
            name = repoDbModel.name,
            language = repoDbModel.language,
            visibility = repoDbModel.visibility
        )

    fun mapRepoDbModelListToRepoList(repoDbModelList: List<RepoDbModel>) : List<Repo> =
        repoDbModelList.map { mapRepoDbModelToRepo(it) }

    fun mapRepoToRepoDbModel(repo: Repo) : RepoDbModel =
        RepoDbModel(
            name = repo.name,
            language = repo.language,
            visibility = repo.visibility
        )

    fun mapRepoListToRepoDbModelList(repoList: List<Repo>) : List<RepoDbModel> =
        repoList.map { mapRepoToRepoDbModel(it) }
}