package com.practicework.login.data.remote_data_source.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.retrofit.call_handler.safeApiCall
import com.practicework.core.room.models.UserEntity
import com.practicework.login.data.remote_data_source.models.UserApiModel
import com.practicework.login.data.remote_data_source.retrofit.LoginApiService
import com.practicework.login.data.source.Mappers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val apiService: LoginApiService
) : LoginRemoteDataSource {
    override fun getUser(): Flow<Resource<UserEntity>> {
        return safeApiCall(
            mapper = { response: UserApiModel ->
                Mappers.mapUserApiModelToUserEntity(response)
            },
            body = { apiService.getUser() }
        )
    }
}