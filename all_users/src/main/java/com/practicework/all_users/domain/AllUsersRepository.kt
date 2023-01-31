package com.practicework.all_users.domain

import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.DbResource
import kotlinx.coroutines.flow.Flow

interface AllUsersRepository {

    suspend fun getUsersFromApi(perPage: Int, page: Int) : Flow<Resource<List<AllUser>>>

    suspend fun insertUsersToDb(list: List<AllUser>)

    fun getUsersFromDb(offset: Int, select: Int) : Flow<DbResource<List<AllUser>>>

    suspend fun updateUsersInDb(list: List<AllUser>)
}