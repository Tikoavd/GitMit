package com.practicework.all_users.domain.usecases

import com.practicework.all_users.domain.AllUsersRepository

class GetUsersFromDbUseCase(private val repository: AllUsersRepository) {
    operator fun invoke(offset: Int, select: Int) = repository.getUsersFromDb(offset, select)
}