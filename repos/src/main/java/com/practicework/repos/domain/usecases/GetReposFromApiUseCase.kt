package com.practicework.repos.domain.usecases

import com.practicework.repos.domain.ReposRepository

class GetReposFromApiUseCase(
    private val reposRepository: ReposRepository
) {
    operator fun invoke(perPage: Int, page: Int) = reposRepository.getRepos(perPage, page)
}