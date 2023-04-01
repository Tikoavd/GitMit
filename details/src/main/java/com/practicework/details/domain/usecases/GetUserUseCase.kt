package com.practicework.details.domain.usecases

import com.practicework.details.domain.DetailsRepository

class GetUserUseCase(private val detailsRepository: DetailsRepository) {
    suspend operator fun invoke(login: String) = detailsRepository.getUser(login)
}