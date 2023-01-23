package com.practicework.login.data.local_data_source

import com.practicework.core.room.call_handler.DbResource
import com.practicework.login.domain.models.User
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getUser() : Flow<DbResource<User>>

    suspend fun deleteUser()

    suspend fun insertUser(user: User)
}