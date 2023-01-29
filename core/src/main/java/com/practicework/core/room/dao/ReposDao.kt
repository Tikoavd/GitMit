package com.practicework.core.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.practicework.core.room.models.RepoDbModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ReposDao {

    @Insert
    fun insertRepos(list: List<RepoDbModel>) : Completable

    @Query("DELETE FROM repos")
    fun clearRepos() : Completable

    @Query("SELECT * FROM repos LIMIT :select OFFSET :offset")
    fun getRepos(offset: Int, select: Int) : Flowable<List<RepoDbModel>>
}