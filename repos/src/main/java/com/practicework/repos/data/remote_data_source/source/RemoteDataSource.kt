package com.practicework.repos.data.remote_data_source.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable

interface RemoteDataSource {

    fun getMyRepos(perPage: Int, page: Int) : Flowable<Resource<List<Repo>>>
}