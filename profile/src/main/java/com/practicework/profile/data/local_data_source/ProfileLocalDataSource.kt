package com.practicework.profile.data.local_data_source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.profile.domain.models.User
import kotlinx.coroutines.flow.Flow

interface ProfileLocalDataSource {

    suspend fun clearDb()

    fun getUser() : Flow<Resource<User>>
}