package com.practicework.login.data.local_data_source

import com.practicework.core.room.call_handler.DbResource
import com.practicework.core.room.call_handler.safeDbCall
import com.practicework.core.room.dao.UserDao
import com.practicework.login.data.source.Mappers
import com.practicework.login.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : LocalDataSource {

    override fun getUser(): Flow<DbResource<User>> {
        return safeDbCall(
            mapper = { Mappers.mapUserDbModelToUser(it) },
            body = { userDao.getUser() }
        )
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }

    override suspend fun insertUser(user: User) {
        userDao.insertUser(Mappers.mapUserToUserDbModel(user))
    }
}