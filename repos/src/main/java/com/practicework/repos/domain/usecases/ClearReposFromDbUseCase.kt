package com.practicework.repos.domain.usecases

import com.practicework.repos.domain.ReposRepository

class ClearReposFromDbUseCase(
    private val reposRepository: ReposRepository
) {
    operator fun invoke() = reposRepository.clearReposFromDb()
}