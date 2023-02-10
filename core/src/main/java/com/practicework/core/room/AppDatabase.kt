package com.practicework.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicework.core.room.dao.UserDao
import com.practicework.core.room.models.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
}