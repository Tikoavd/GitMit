package com.practicework.details.data.source

import com.practicework.details.data.remote_data_source.models.RepoApiModel
import com.practicework.details.data.remote_data_source.models.UserApiModel
import com.practicework.details.domain.models.Repo
import com.practicework.details.domain.models.User

object Mappers {

    fun mapRepoApiModelToRepo(repoApiModel: RepoApiModel): Repo =
        Repo(
            name = repoApiModel.name,
            language = repoApiModel.language ?: "unknown",
            visibility = repoApiModel.visibility
        )

    fun mapRepoApiModelListToRepoList(repoApiModelList: List<RepoApiModel>) : List<Repo> =
        repoApiModelList.map { mapRepoApiModelToRepo(it) }

    fun mapUserApiModelToUser(userApiModel: UserApiModel): User =
        User(
            login = userApiModel.login,
            avatarUrl = userApiModel.avatarUrl,
            followersCount = userApiModel.followersCount,
            followingCount = userApiModel.followingCount
        )
}