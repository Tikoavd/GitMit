package com.practicework.core.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.practicework.core.room.models.RepoConfigs
import com.practicework.core.room.models.RepoEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ReposDao {

    @Insert
    fun insertRepos(list: List<RepoEntity>) : Completable

    @Query("DELETE FROM ${RepoConfigs.TABLE_NAME}")
    fun clearRepos() : Completable

    @Query("SELECT * FROM ${RepoConfigs.TABLE_NAME} LIMIT :select OFFSET :offset")
    fun getRepos(offset: Int, select: Int) : Flowable<List<RepoEntity>>
}