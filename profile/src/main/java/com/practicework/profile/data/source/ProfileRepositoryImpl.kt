package com.practicework.profile.data.source

import com.practicework.core.room.call_handler.DbResource
import com.practicework.profile.data.local_data_source.LocalDataSource
import com.practicework.profile.domain.ProfileRepository
import com.practicework.profile.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : ProfileRepository {
    override suspend fun clearAllData() {
        localDataSource.clearDb()
    }

    override fun getUserFromDb(): Flow<DbResource<User>> {
        return localDataSource.getUser()
    }
}