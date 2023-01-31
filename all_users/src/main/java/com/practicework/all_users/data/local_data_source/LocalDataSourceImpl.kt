package com.practicework.all_users.data.local_data_source

import com.practicework.all_users.data.source.Mappers
import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.room.call_handler.DbResource
import com.practicework.core.room.call_handler.safeDbCall
import com.practicework.core.room.dao.AllUsersDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class LocalDataSourceImpl @Inject constructor(
    private val allUsersDao: AllUsersDao
) : LocalDataSource {
    private val context by lazy { Dispatchers.IO }

    override suspend fun insertAllUsers(list: List<AllUser>) {
        allUsersDao.insertAllUsers(Mappers.mapAllUserListToAllUserDbModelList(list))
    }

    override fun getAllUsers(offset: Int, select: Int): Flow<DbResource<List<AllUser>>> {
        return safeDbCall(
            mapper = { Mappers.mapAllUserDbModelListToAllUserList(it) },
            body = { allUsersDao.getAllUsers(offset, select) }
        )
    }

    override suspend fun updateAllUsers(list: List<AllUser>) {
        coroutineScope {
            val result = async(Dispatchers.IO) { allUsersDao.clearAllUsers() }
            if (result.await() >= 0) {
                insertAllUsers(list)
            }
        }
    }
}