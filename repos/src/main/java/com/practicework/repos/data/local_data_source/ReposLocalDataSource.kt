package com.practicework.repos.data.local_data_source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable

interface ReposLocalDataSource {

    fun insertRepos(list: List<Repo>)

    fun getRepos(offset: Int, select: Int) : Flowable<Resource<List<Repo>>>

    fun updateRepos(list: List<Repo>)
}