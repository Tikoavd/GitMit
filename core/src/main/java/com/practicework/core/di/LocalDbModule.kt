package com.practicework.core.di

import android.content.Context
import androidx.room.Room
import com.practicework.core.room.AppDatabase
import com.practicework.core.room.dao.AllUsersDao
import com.practicework.core.room.dao.ReposDao
import com.practicework.core.room.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDbModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) : UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideReposDao(appDatabase: AppDatabase) : ReposDao {
        return appDatabase.reposDao()
    }

    @Provides
    @Singleton
    fun provideAllUsersDao(appDatabase: AppDatabase) : AllUsersDao {
        return appDatabase.allUsersDao()
    }
}