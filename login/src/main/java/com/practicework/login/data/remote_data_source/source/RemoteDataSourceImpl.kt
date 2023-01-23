package com.practicework.login.data.remote_data_source.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.retrofit.call_handler.safeApiCall
import com.practicework.login.data.remote_data_source.models.UserApiModel
import com.practicework.login.data.remote_data_source.retrofit.LoginApiService
import com.practicework.login.data.source.Mappers
import com.practicework.login.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: LoginApiService)
    : RemoteDataSource {
    override suspend fun getUser(): Flow<Resource<User>> {
        return safeApiCall(
            mapper = { response: UserApiModel ->
                Mappers.mapUserApiModelToUser(response) },
            body = { apiService.getUser() }
        )
    }
}