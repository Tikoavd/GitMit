package com.practicework.login.domain.usecases

import com.practicework.login.domain.LoginRepository
import com.practicework.login.domain.models.User

class InsertUserToDbUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(user: User) =  loginRepository.insertUserToDb(user)
}