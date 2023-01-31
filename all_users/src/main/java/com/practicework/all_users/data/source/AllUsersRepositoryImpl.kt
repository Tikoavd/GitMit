package com.practicework.all_users.data.source

import com.practicework.all_users.data.local_data_source.LocalDataSource
import com.practicework.all_users.data.remote_data_source.source.RemoteDataSource
import com.practicework.all_users.domain.AllUsersRepository
import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.DbResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllUsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AllUsersRepository {
    override suspend fun getUsersFromApi(perPage: Int, since: Int): Flow<Resource<List<AllUser>>> =
        remoteDataSource.getUsers(perPage, since)

    override suspend fun insertUsersToDb(list: List<AllUser>) {
        localDataSource.insertAllUsers(list)
    }

    override fun getUsersFromDb(offset: Int, select: Int): Flow<DbResource<List<AllUser>>> {
        return localDataSource.getAllUsers(offset, select)
    }

    override suspend fun updateUsersInDb(list: List<AllUser>) {
        localDataSource.updateAllUsers(list)
    }
}