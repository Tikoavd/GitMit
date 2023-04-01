package com.practicework.login.data.local_data_source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.models.UserEntity
import kotlinx.coroutines.flow.Flow

interface LoginLocalDataSource {

    fun getUser() : Flow<Resource<UserEntity>>

    suspend fun deleteUser()

    suspend fun insertUser(userEntity: UserEntity)

    fun saveToken(token: String)
}