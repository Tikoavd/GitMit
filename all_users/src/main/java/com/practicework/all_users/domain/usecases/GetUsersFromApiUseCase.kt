package com.practicework.all_users.domain.usecases

import com.practicework.all_users.domain.AllUsersRepository

class GetUsersFromApiUseCase(private val allUsersRepository: AllUsersRepository) {
    suspend operator fun invoke(perPage: Int, since: Int) =
        allUsersRepository.getUsersFromApi(perPage, since)
}