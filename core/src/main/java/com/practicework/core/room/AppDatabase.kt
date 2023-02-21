package com.practicework.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicework.core.room.dao.ReposDao
import com.practicework.core.room.dao.UserDao
import com.practicework.core.room.models.RepoEntity
import com.practicework.core.room.models.UserEntity

@Database(entities = [UserEntity::class, RepoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun reposDao() : ReposDao
}