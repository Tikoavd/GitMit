package com.practicework.all_users.data.remote_data_source.source

import com.practicework.all_users.data.remote_data_source.retrofit.AllUsersApiService
import com.practicework.all_users.data.source.Mappers
import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.retrofit.call_handler.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: AllUsersApiService
) : RemoteDataSource {
    override suspend fun getUsers(perPage: Int, since: Int): Flow<Resource<List<AllUser>>> {
        return safeApiCall(
            mapper = { Mappers.mapAllUserApiModelListToAllUserList(it) },
            body = { apiService.getUsers(perPage, since) }
        )
    }
}