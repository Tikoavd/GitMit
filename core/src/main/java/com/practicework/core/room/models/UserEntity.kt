package com.practicework.core.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserConfigs.TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = UserConfigs.ID)
    val id: Int = 0,
    @ColumnInfo(name = UserConfigs.LOGIN)
    val login: String,
    @ColumnInfo(name = UserConfigs.FOLLOWERS_COUNT)
    val followersCount: Int,
    @ColumnInfo(name = UserConfigs.FOLLOWING_COUNT)
    val followingCount: Int
) {
    companion object {
        const val NO_LOGIN = "unknown login"
        const val NO_FOLLOWS = 0
    }
}