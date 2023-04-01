package com.practicework.profile.data.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.profile.data.local_data_source.ProfileLocalDataSource
import com.practicework.profile.domain.ProfileRepository
import com.practicework.profile.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val localDataSource: ProfileLocalDataSource
) : ProfileRepository {
    override suspend fun clearAllData() {
        localDataSource.clearDb()
    }

    override fun getUserFromDb(): Flow<Resource<User>> {
        return localDataSource.getUser()
    }
}