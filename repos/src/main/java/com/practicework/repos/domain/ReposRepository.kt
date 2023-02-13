package com.practicework.repos.domain

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable

interface ReposRepository {

    fun getRepos(perPage: Int, page: Int, offset: Int) : Flowable<Resource<List<Repo>>>
}