package com.practicework.repos.data.source.mappers

import com.practicework.core.mappers.Mapper
import com.practicework.core.room.models.RepoEntity
import com.practicework.repos.domain.models.Repo
import javax.inject.Inject

class RepoToEntityMapper @Inject constructor() : Mapper<Repo, RepoEntity> {
    override fun map(input: Repo): RepoEntity =
        RepoEntity(
            name = input.name,
            language = input.language,
            visibility = input.visibility
        )
}