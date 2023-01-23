package com.practicework.login.data.remote_data_source.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.login.domain.models.User
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getUser() : Flow<Resource<User>>
}