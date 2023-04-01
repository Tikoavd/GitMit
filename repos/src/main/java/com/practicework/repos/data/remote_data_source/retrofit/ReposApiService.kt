package com.practicework.repos.data.remote_data_source.retrofit

import com.practicework.repos.data.remote_data_source.models.RepoApiModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposApiService {
    @GET(API_REPOS)
    fun getMyRepos(@Query(PER_PAGE) perPage: Int, @Query(PAGE) page: Int)
                : Single<List<RepoApiModel>>
}
