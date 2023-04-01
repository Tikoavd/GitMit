package com.practicework.details.domain

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.details.domain.models.Repo
import com.practicework.details.domain.models.User
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    suspend fun getUser(login: String) : Flow<Resource<User>>

    suspend fun getRepos(login: String, perPage: Int, page: Int) : Flow<Resource<List<Repo>>>
}