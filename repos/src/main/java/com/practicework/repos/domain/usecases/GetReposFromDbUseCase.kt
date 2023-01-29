package com.practicework.repos.domain.usecases

import com.practicework.repos.domain.ReposRepository

class GetReposFromDbUseCase(
    private val reposRepository: ReposRepository
) {
    operator fun invoke(offset: Int, select: Int) = reposRepository.getReposFromDb(offset, select)
}