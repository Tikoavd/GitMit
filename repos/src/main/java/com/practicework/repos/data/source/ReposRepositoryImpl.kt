package com.practicework.repos.data.source

import com.practicework.core.retrofit.call_handler.ErrorTypes
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.rx.CommonRxThreads
import com.practicework.repos.data.local_data_source.ReposLocalDataSource
import com.practicework.repos.data.remote_data_source.source.ReposRemoteDataSource
import com.practicework.repos.domain.ReposRepository
import com.practicework.repos.domain.models.Repo
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: ReposRemoteDataSource,
    private val localDataSource: ReposLocalDataSource,
    private val rxThreads: CommonRxThreads
) : ReposRepository {
    override fun getRepos(perPage: Int, page: Int, offset: Int): Flowable<Resource<List<Repo>>> {
        return Flowable.create({ subscriber ->
            remoteDataSource.getMyRepos(perPage, page)
                .subscribeOn(rxThreads.ioThread)
                .subscribe { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            if (resource.exception.message
                                == ErrorTypes.NO_INTERNET_ACCESS.message
                            ) {
                                localDataSource.getRepos(offset, perPage)
                                    .subscribeOn(rxThreads.ioThread)
                                    .subscribe {
                                        subscriber.onNext(it)
                                    }
                            }
                        }
                        Resource.Loading -> {
                            subscriber.onNext(resource)
                        }
                        is Resource.Success -> {
                            if (page == 1) {
                                localDataSource.updateRepos(resource.model)
                            } else {
                                localDataSource.insertRepos(resource.model)
                            }
                            subscriber.onNext(resource)
                        }
                    }
                }
        }, BackpressureStrategy.LATEST)
    }
}
