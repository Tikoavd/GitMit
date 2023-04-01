package com.practicework.core.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practicework.core.room.models.UserConfigs
import com.practicework.core.room.models.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM ${UserConfigs.TABLE_NAME} LIMIT 1")
    fun getUser() : Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM ${UserConfigs.TABLE_NAME}")
    suspend fun deleteUser()
}