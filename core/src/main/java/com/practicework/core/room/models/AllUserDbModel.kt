package com.practicework.core.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_users")
data class AllUserDbModel(
    val login: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
