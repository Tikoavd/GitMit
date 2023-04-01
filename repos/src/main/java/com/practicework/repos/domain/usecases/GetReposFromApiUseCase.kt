package com.practicework.repos.domain.usecases

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.repos.domain.ReposRepository
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable

class GetReposFromApiUseCase(
    private val reposRepository: ReposRepository
) {
    operator fun invoke(perPage: Int, page: Int): Flowable<Resource<List<Repo>>> {
        val offset = (page - 1) * perPage
        return reposRepository.getRepos(perPage, page, offset)
    }
}