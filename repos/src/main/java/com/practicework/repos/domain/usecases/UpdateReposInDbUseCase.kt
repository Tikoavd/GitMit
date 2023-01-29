package com.practicework.repos.domain.usecases

import com.practicework.repos.domain.ReposRepository
import com.practicework.repos.domain.models.Repo

class UpdateReposInDbUseCase(
    private val reposRepository: ReposRepository
) {
    operator fun invoke(list: List<Repo>) = reposRepository.updateReposInDb(list)
}