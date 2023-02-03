package com.practicework.profile.data.local_data_source

import com.practicework.core.room.call_handler.DbResource
import com.practicework.profile.domain.models.User
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun clearDb()

    fun getUser() : Flow<DbResource<User>>
}