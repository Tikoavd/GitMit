package com.practicework.login.domain

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.DbResource
import com.practicework.login.domain.models.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun getUserFromApi() : Flow<Resource<User>>

    fun getUserFromDb() : Flow<DbResource<User>>

    suspend fun insertUserToDb(user: User)

    suspend fun deleteUserFromDb()
}