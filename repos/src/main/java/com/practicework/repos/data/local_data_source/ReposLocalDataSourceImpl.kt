package com.practicework.repos.data.local_data_source

import com.practicework.core.mappers.ListMapper
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.safeRxDbCall
import com.practicework.core.room.dao.ReposDao
import com.practicework.core.room.models.RepoEntity
import com.practicework.core.rx.CommonRxThreads
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ReposLocalDataSourceImpl @Inject constructor(
    private val reposDao: ReposDao,
    private val rxThreads: CommonRxThreads,
    private val mapperToRepo: ListMapper<RepoEntity, Repo>,
    private val mapperToEntity: ListMapper<Repo, RepoEntity>
) : ReposLocalDataSource {
    override fun insertRepos(list: List<Repo>) {
        reposDao.insertRepos(mapperToEntity.map(list))
            .subscribeOn(rxThreads.ioThread)
            .subscribe()
    }

    override fun getRepos(offset: Int, select: Int): Flowable<Resource<List<Repo>>> {
        return safeRxDbCall(
            mapper = { mapperToRepo.map(it) },
            body = { reposDao.getRepos(offset, select) }
        )
    }

    override fun updateRepos(list: List<Repo>) {
        reposDao.clearRepos().subscribeOn(rxThreads.ioThread)
            .subscribe { insertRepos(list) }
    }
}