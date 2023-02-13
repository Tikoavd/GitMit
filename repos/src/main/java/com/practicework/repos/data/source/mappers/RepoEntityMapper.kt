package com.practicework.repos.data.source.mappers

import com.practicework.core.mappers.Mapper
import com.practicework.core.room.models.RepoEntity
import com.practicework.repos.domain.models.Repo
import javax.inject.Inject

class RepoEntityMapper @Inject constructor() : Mapper<RepoEntity, Repo> {
    override fun map(input: RepoEntity): Repo =
        Repo(
            name = input.name,
            language = input.language,
            visibility = input.visibility
        )
}