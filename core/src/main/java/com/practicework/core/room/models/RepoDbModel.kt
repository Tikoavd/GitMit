package com.practicework.core.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoDbModel(
    val name: String,
    val language: String,
    val visibility: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
