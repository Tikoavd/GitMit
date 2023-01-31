package com.practicework.all_users.data.local_data_source

import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.room.call_handler.DbResource
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertAllUsers(list: List<AllUser>)

    fun getAllUsers(offset: Int, select: Int) : Flow<DbResource<List<AllUser>>>

    suspend fun updateAllUsers(list: List<AllUser>)
}