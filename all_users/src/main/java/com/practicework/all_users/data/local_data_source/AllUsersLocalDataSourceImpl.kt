package com.practicework.all_users.data.local_data_source

import com.practicework.all_users.data.source.Mappers
import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.coroutines.CommonDispatchers
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.safeDbCall
import com.practicework.core.room.dao.AllUsersDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllUsersLocalDataSourceImpl @Inject constructor(
    private val allUsersDao: AllUsersDao,
    private val commonDispatchers: CommonDispatchers
) : AllUsersLocalDataSource {

    override suspend fun insertAllUsers(list: List<AllUser>) {
        allUsersDao.insertAllUsers(Mappers.mapAllUserListToAllUserEntityList(list))
    }

    override fun getAllUsers(offset: Int, select: Int): Flow<Resource<List<AllUser>>> {
        return safeDbCall(
            mapper = { Mappers.mapAllUserEntityListToAllUserList(it) },
            body = { allUsersDao.getAllUsers(offset, select) }
        )
    }

    override suspend fun updateAllUsers(list: List<AllUser>) {
        coroutineScope {
            val result = async(commonDispatchers.ioDispatcher) {
                allUsersDao.clearAllUsers()
            }
            if (result.await() >= 0) {
                insertAllUsers(list)
            }
        }
    }
}