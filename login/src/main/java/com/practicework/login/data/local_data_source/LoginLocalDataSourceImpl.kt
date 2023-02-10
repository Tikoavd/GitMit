package com.practicework.login.data.local_data_source

import android.content.SharedPreferences
import com.practicework.core.di.TOKEN
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.safeDbCall
import com.practicework.core.room.dao.UserDao
import com.practicework.core.room.models.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val sharedPreferences: SharedPreferences
) : LoginLocalDataSource {

    override fun getUser(): Flow<Resource<UserEntity>> {
        return safeDbCall(
            mapper = { it },
            body = { userDao.getUser() }
        )
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }

    override suspend fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString(TOKEN, token)
            .apply()
    }
}