package com.practicework.login.data.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.login.data.local_data_source.LoginLocalDataSource
import com.practicework.login.data.remote_data_source.source.LoginRemoteDataSource
import com.practicework.login.domain.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: LoginLocalDataSource,
) : LoginRepository {

    override fun tryToSignIn(token: String): Flow<Resource<Unit>> {
        localDataSource.saveToken(token)
        return remoteDataSource.getUser().map { resource ->
            when (resource) {
                is Resource.Error -> {
                    Resource.Error(resource.exception, null)
                }
                Resource.Loading -> {
                    Resource.Loading
                }
                is Resource.Success -> {
                    localDataSource.insertUser(resource.model)
                    Resource.Success(Unit)
                }
            }
        }
    }

    override fun tryToSignInFromDb(): Flow<Resource<Unit>> {
        return localDataSource.getUser().map { resource ->
            when (resource) {
                is Resource.Error -> {
                    Resource.Error(resource.exception, null)
                }
                Resource.Loading -> {
                    Resource.Loading
                }
                is Resource.Success -> {
                    Resource.Success(Unit)
                }
            }
        }
    }
}