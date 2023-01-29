package com.practicework.repos.domain

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.DbResource
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable

interface ReposRepository {

    fun getReposFromApi(perPage: Int, page: Int) : Flowable<Resource<List<Repo>>>

    fun insertReposToDb(list: List<Repo>)

    fun clearReposFromDb()

    fun getReposFromDb(offset: Int, select: Int) : Flowable<DbResource<List<Repo>>>

    fun updateReposInDb(list: List<Repo>)
}