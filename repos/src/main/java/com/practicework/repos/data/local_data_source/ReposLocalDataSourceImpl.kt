package com.practicework.repos.data.local_data_source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.safeRxDbCall
import com.practicework.core.room.dao.ReposDao
import com.practicework.repos.data.source.Mappers
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ReposLocalDataSourceImpl @Inject constructor(
    private val reposDao: ReposDao
) : ReposLocalDataSource {
    override fun insertRepos(list: List<Repo>) {
        reposDao.insertRepos(Mappers.mapRepoListToRepoEntityList(list))
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun clearRepos() {
        reposDao.clearRepos().subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getRepos(offset: Int, select: Int): Flowable<Resource<List<Repo>>> {
        return safeRxDbCall(
            mapper = { Mappers.mapRepoEntityListToRepoList(it) },
            body = { reposDao.getRepos(offset, select) }
        )
    }

    override fun updateRepos(list: List<Repo>) {
        reposDao.clearRepos().subscribeOn(Schedulers.io())
            .subscribe { insertRepos(list) }
    }
}