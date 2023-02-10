package com.practicework.repos.data.remote_data_source.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.retrofit.call_handler.safeRxApiCall
import com.practicework.repos.data.remote_data_source.retrofit.ReposApiService
import com.practicework.repos.data.source.Mappers
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ReposRemoteDataSourceImpl @Inject constructor(
    private val apiService: ReposApiService
) : ReposRemoteDataSource {

    override fun getMyRepos(perPage: Int, page: Int): Flowable<Resource<List<Repo>>> {
        return safeRxApiCall(
            mapper = { Mappers.mapRepoApiModelListToRepoList(it) },
            body = { apiService.getMyRepos(perPage, page) }
        )
    }
}