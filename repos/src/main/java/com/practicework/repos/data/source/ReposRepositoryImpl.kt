package com.practicework.repos.data.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.call_handler.DbResource
import com.practicework.repos.data.local_data_source.LocalDataSource
import com.practicework.repos.data.remote_data_source.source.RemoteDataSource
import com.practicework.repos.domain.ReposRepository
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ReposRepository {
    override fun getReposFromApi(perPage: Int, page: Int): Flowable<Resource<List<Repo>>> {
        return remoteDataSource.getMyRepos(perPage, page)
    }

    override fun insertReposToDb(list: List<Repo>) {
        localDataSource.insertRepos(list)
    }

    override fun clearReposFromDb() {
        localDataSource.clearRepos()
    }

    override fun getReposFromDb(offset: Int, select: Int): Flowable<DbResource<List<Repo>>> {
        return localDataSource.getRepos(offset, select)
    }

    override fun updateReposInDb(list: List<Repo>) {
        localDataSource.updateRepos(list)
    }
}