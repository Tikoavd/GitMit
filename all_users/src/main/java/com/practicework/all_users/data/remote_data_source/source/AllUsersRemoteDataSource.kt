package com.practicework.all_users.data.remote_data_source.source

import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.retrofit.call_handler.Resource
import kotlinx.coroutines.flow.Flow

interface AllUsersRemoteDataSource {

    suspend fun getUsers(perPage: Int, since: Int) : Flow<Resource<List<AllUser>>>
}