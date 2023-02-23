package com.practicework.all_users.domain.usecases

import com.practicework.all_users.domain.AllUsersRepository
import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.retrofit.call_handler.Resource
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val allUsersRepository: AllUsersRepository) {
    operator fun invoke(perPage: Int, page: Int): Flow<Resource<List<AllUser>>> {
        val since = (page - 1) * perPage
        return allUsersRepository.getUsers(perPage, since)
    }
}