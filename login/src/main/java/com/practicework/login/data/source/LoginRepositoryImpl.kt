package com.practicework.login.data.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.DbResource
import com.practicework.login.data.local_data_source.LocalDataSource
import com.practicework.login.data.remote_data_source.source.RemoteDataSource
import com.practicework.login.domain.LoginRepository
import com.practicework.login.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : LoginRepository {

    override suspend fun getUserFromApi(): Flow<Resource<User>> {
        return remoteDataSource.getUser()
    }

    override fun getUserFromDb(): Flow<DbResource<User>> {
        return localDataSource.getUser()
    }

    override suspend fun insertUserToDb(user: User) {
        localDataSource.insertUser(user)
    }

    override suspend fun deleteUserFromDb() {
        localDataSource.deleteUser()
    }
}