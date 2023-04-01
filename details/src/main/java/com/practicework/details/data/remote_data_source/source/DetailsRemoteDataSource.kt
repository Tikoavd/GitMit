package com.practicework.details.data.remote_data_source.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.details.domain.models.Repo
import com.practicework.details.domain.models.User
import kotlinx.coroutines.flow.Flow

interface DetailsRemoteDataSource {

    suspend fun getUser(login: String) : Flow<Resource<User>>

    suspend fun getRepos(login: String, perPage: Int, page: Int) : Flow<Resource<List<Repo>>>
}