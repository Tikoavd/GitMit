package com.practicework.login.data.remote_data_source.source

import com.practicework.core.retrofit.call_handler.Resource
import com.practicework.core.room.models.UserEntity
import kotlinx.coroutines.flow.Flow

interface LoginRemoteDataSource {

    fun getUser() : Flow<Resource<UserEntity>>
}