package com.practicework.details.data.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.details.data.remote_data_source.source.RemoteDataSource
import com.practicework.details.domain.DetailsRepository
import com.practicework.details.domain.models.Repo
import com.practicework.details.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : DetailsRepository {
    override suspend fun getUser(login: String): Flow<Resource<User>> {
        return remoteDataSource.getUser(login)
    }

    override suspend fun getRepos(
        login: String,
        perPage: Int,
        page: Int
    ): Flow<Resource<List<Repo>>> {
        return remoteDataSource.getRepos(login = login, perPage = perPage, page = page)
    }
}