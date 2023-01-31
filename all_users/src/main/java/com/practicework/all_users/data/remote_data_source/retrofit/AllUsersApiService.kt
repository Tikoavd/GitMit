package com.practicework.all_users.data.remote_data_source.retrofit

import com.practicework.all_users.data.remote_data_source.models.AllUserApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AllUsersApiService {
    @GET(API_USERS)
    suspend fun getUsers(@Query(PER_PAGE) perPage: Int, @Query(SINCE) since: Int)
                    : Response<List<AllUserApiModel>>
}