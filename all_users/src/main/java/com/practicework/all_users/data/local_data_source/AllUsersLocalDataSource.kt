package com.practicework.all_users.data.local_data_source

import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.retrofit.call_handler.Resource
import kotlinx.coroutines.flow.Flow

interface AllUsersLocalDataSource {

    suspend fun insertAllUsers(list: List<AllUser>)

    fun getAllUsers(offset: Int, select: Int) : Flow<Resource<List<AllUser>>>

    suspend fun updateAllUsers(list: List<AllUser>)
}