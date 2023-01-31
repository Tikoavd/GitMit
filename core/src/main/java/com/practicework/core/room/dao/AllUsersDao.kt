package com.practicework.core.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.practicework.core.room.models.AllUserDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AllUsersDao {
    @Insert
    suspend fun insertAllUsers(list: List<AllUserDbModel>)

    @Query("DELETE FROM all_users")
    suspend fun clearAllUsers() : Int

    @Query("SELECT * FROM all_users LIMIT :select OFFSET :offset")
    fun getAllUsers(offset: Int, select: Int) : Flow<List<AllUserDbModel>>
}