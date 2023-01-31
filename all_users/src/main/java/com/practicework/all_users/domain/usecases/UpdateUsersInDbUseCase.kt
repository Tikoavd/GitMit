package com.practicework.all_users.domain.usecases

import com.practicework.all_users.domain.AllUsersRepository
import com.practicework.all_users.domain.models.AllUser

class UpdateUsersInDbUseCase(private val repository: AllUsersRepository) {
    suspend operator fun invoke(list: List<AllUser>) = repository.updateUsersInDb(list)
}