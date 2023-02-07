package com.practicework.login.data.remote_data_source.retrofit

import com.practicework.login.data.remote_data_source.models.UserApiModel
import retrofit2.Response
import retrofit2.http.GET

interface LoginApiService {
    @GET(ApiConfig.USER_API)
    suspend fun getUser(): Response<UserApiModel>
}