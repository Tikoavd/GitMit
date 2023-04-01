package com.practicework.profile.data.local_data_source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.safeDbCall
import com.practicework.core.room.dao.AllUsersDao
import com.practicework.core.room.dao.ReposDao
import com.practicework.core.room.dao.UserDao
import com.practicework.profile.data.source.Mappers
import com.practicework.profile.domain.models.User
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileLocalDataSourceImpl @Inject constructor(
    private val userDao : UserDao,
    private val reposDao : ReposDao,
    private val allUsersDao: AllUsersDao
) : ProfileLocalDataSource {

    override suspend fun clearDb() {
        userDao.deleteUser()
        reposDao.clearRepos().subscribeOn(Schedulers.io()).subscribe()
        allUsersDao.clearAllUsers()
    }

    override fun getUser(): Flow<Resource<User>> {
        return safeDbCall(
            mapper = { Mappers.mapUserDbModelToUser(it) },
            body = { userDao.getUser() }
        )
    }
}