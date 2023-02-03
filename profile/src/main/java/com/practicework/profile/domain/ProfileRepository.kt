package com.practicework.profile.domain

import com.practicework.core.room.call_handler.DbResource
import com.practicework.profile.domain.models.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun clearAllData()

    fun getUserFromDb() : Flow<DbResource<User>>
}