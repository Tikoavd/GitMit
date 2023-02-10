package com.practicework.core.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RepoConfigs.TABLE_NAME)
data class RepoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RepoConfigs.ID)
    val id: Int = 0,
    @ColumnInfo(name = RepoConfigs.NAME)
    val name: String,
    @ColumnInfo(name = RepoConfigs.LANGUAGE)
    val language: String,
    @ColumnInfo(name = RepoConfigs.VISIBILITY)
    val visibility: String
)
