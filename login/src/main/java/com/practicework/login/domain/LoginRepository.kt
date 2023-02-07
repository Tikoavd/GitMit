package com.practicework.login.domain

import com.practicework.core.retrofit.call_handler.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun tryToSignIn(token: String) : Flow<Resource<Unit>>

    fun tryToSignInFromDb(): Flow<Resource<Unit>>
}