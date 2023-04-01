package com.practicework.profile.domain

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.profile.domain.models.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun clearAllData()

    fun getUserFromDb() : Flow<Resource<User>>
}