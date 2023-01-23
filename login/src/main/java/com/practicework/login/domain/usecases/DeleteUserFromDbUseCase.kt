package com.practicework.login.domain.usecases

import com.practicework.login.domain.LoginRepository

class DeleteUserFromDbUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke() = loginRepository.deleteUserFromDb()
}