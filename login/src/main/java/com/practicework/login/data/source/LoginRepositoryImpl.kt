package com.practicework.login.data.source

import com.practicework.core.coroutines.CommonDispatchers
import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.login.data.local_data_source.LoginLocalDataSource
import com.practicework.login.data.remote_data_source.source.LoginRemoteDataSource
import com.practicework.login.domain.LoginRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: LoginLocalDataSource,
    private val dispatchers: CommonDispatchers
) : LoginRepository {

    override fun tryToSignIn(token: String): Flow<Resource<Unit>> {
        localDataSource.saveToken(token)
        return flow {
            remoteDataSource.getUser()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            emit(Resource.Error(resource.exception, null))
                        }
                        Resource.Loading -> {
                            emit(Resource.Loading)
                        }
                        is Resource.Success -> {
                            localDataSource.insertUser(resource.model)
                            emit(Resource.Success(Unit))
                        }
                    }
                }
                .flowOn(dispatchers.ioDispatcher)
                .collect()
        }
    }

    override fun tryToSignInFromDb(): Flow<Resource<Unit>> {
        return flow {
            localDataSource.getUser()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            emit(Resource.Error(resource.exception, null))
                        }
                        Resource.Loading -> {
                            emit(Resource.Loading)
                        }
                        is Resource.Success -> {
                            emit(Resource.Success(Unit))
                        }
                    }
                }
                .flowOn(dispatchers.ioDispatcher)
                .collect()
        }
    }
}