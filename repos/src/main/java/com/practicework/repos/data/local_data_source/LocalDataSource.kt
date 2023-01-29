package com.practicework.repos.data.local_data_source

import com.practicework.core.room.call_handler.DbResource
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable

interface LocalDataSource {

    fun insertRepos(list: List<Repo>)

    fun clearRepos()

    fun getRepos(offset: Int, select: Int) : Flowable<DbResource<List<Repo>>>

    fun updateRepos(list: List<Repo>)
}