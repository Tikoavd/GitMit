package com.practicework.login.domain.usecases

import com.practicework.login.domain.LoginRepository

class GetUserFromDbUseCase(private val loginRepository: LoginRepository) {
    operator fun invoke() = loginRepository.getUserFromDb()
}