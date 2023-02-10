package com.practicework.login.domain.usecases

import com.practicework.login.domain.LoginRepository

class TryToSignInUseCase(private val loginRepository: LoginRepository) {
    operator fun invoke(token: String) = loginRepository.tryToSignIn(token)
}