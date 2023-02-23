package com.practicework.core.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AllUserConfigs.TABLE_NAME)
data class AllUserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = AllUserConfigs.COLUMN_ID)
    val id: Int = 0,
    @ColumnInfo(name = AllUserConfigs.COLUMN_LOGIN)
    val login: String
)
