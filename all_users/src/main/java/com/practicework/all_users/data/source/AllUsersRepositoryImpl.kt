package com.practicework.all_users.data.source

import com.practicework.all_users.data.local_data_source.AllUsersLocalDataSource
import com.practicework.all_users.data.remote_data_source.source.AllUsersRemoteDataSource
import com.practicework.all_users.domain.AllUsersRepository
import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.coroutines.CommonDispatchers
import com.practicework.core.retrofit.call_handler.ErrorTypes
import com.practicework.core.retrofit.call_handler.Resource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AllUsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: AllUsersRemoteDataSource,
    private val localDataSource: AllUsersLocalDataSource,
    private val commonDispatchers: CommonDispatchers
) : AllUsersRepository {

    override fun getUsers(perPage: Int, since: Int): Flow<Resource<List<AllUser>>> {
        return flow {
            remoteDataSource.getUsers(perPage, since)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            if (resource.exception.message
                                == ErrorTypes.NO_INTERNET_ACCESS.message
                            ) {
                                emitAll(
                                    localDataSource.getAllUsers(offset = since, select = perPage)
                                )
                            } else emit(resource)
                        }
                        Resource.Loading -> {
                            emit(resource)
                        }
                        is Resource.Success -> {
                            if (since == 0) {
                                localDataSource.updateAllUsers(resource.model)
                            } else {
                                localDataSource.insertAllUsers(resource.model)
                            }
                            emit(resource)
                        }
                    }
                }
                .flowOn(commonDispatchers.ioDispatcher)
                .collect()
        }
    }
}