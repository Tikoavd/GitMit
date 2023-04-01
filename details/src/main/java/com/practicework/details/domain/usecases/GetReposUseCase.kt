package com.practicework.details.domain.usecases

import com.practicework.details.domain.DetailsRepository

class GetReposUseCase(private val detailsRepository: DetailsRepository) {
    suspend operator fun invoke(login: String, perPage: Int, page: Int) =
        detailsRepository.getRepos(login = login, perPage = perPage, page = page)
}