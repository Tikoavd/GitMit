package com.practicework.details.data.remote_data_source.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.retrofit.call_handler.safeApiCall
import com.practicework.details.data.remote_data_source.retrofit.DetailsApiService
import com.practicework.details.data.source.Mappers
import com.practicework.details.domain.models.Repo
import com.practicework.details.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: DetailsApiService
) : RemoteDataSource {

    override suspend fun getUser(login: String): Flow<Resource<User>> {
        return safeApiCall(
            mapper = { Mappers.mapUserApiModelToUser(it) },
            body = { apiService.getUser(login) }
        )
    }

    override suspend fun getRepos(
        login: String,
        perPage: Int,
        page: Int
    ): Flow<Resource<List<Repo>>> {
        return safeApiCall(
            mapper = { Mappers.mapRepoApiModelListToRepoList(it) },
            body = { apiService.getRepos(login = login, perPage = perPage, page = page) }
        )
    }
}