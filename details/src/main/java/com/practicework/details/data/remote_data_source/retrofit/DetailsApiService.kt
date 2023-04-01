package com.practicework.details.data.remote_data_source.retrofit

import com.practicework.details.data.remote_data_source.models.RepoApiModel
import com.practicework.details.data.remote_data_source.models.UserApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsApiService {
    @GET(API_USER)
    suspend fun getUser(@Path(LOGIN_PATH) login: String) : Response<UserApiModel>

    @GET(API_USER_REPOS)
    suspend fun getRepos(
        @Path(LOGIN_PATH) login: String,
        @Query(PER_PAGE) perPage: Int,
        @Query(PAGE) page: Int
    ) : Response<List<RepoApiModel>>
}