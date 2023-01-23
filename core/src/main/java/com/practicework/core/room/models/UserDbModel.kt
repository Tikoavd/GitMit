package com.practicework.core.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDbModel(
    val login: String,
    val followersCount: Int,
    val followingCount: Int,
    @PrimaryKey
    val id: Int = 0
)