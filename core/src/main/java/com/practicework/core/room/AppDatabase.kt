package com.practicework.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicework.core.room.dao.UserDao
import com.practicework.core.room.models.UserDbModel

@Database(entities = [UserDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
}