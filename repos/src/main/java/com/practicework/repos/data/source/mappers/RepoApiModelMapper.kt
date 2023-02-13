package com.practicework.repos.data.source.mappers

import com.practicework.core.mappers.Mapper
import com.practicework.repos.data.remote_data_source.models.RepoApiModel
import com.practicework.repos.domain.models.Repo
import javax.inject.Inject

class RepoApiModelMapper @Inject constructor() : Mapper<RepoApiModel, Repo> {
    override fun map(input: RepoApiModel): Repo =
        Repo(
            name = input.name ?: Repo.UNKNOWN_FIELD_VALUE,
            language = input.language ?: "unknown",
            visibility = input.visibility ?: Repo.UNKNOWN_FIELD_VALUE
        )
}