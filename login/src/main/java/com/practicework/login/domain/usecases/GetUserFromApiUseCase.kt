package com.practicework.login.domain.usecases

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.login.domain.LoginRepository
import com.practicework.login.domain.models.User
import kotlinx.coroutines.flow.Flow

class GetUserFromApiUseCase(private val loginRepository: LoginRepository) {

    suspend operator fun invoke() : Flow<Resource<User>> {
        return loginRepository.getUserFromApi()
    }
}