package com.practicework.all_users.domain

import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.retrofit.call_handler.Resource
import kotlinx.coroutines.flow.Flow

interface AllUsersRepository {

    fun getUsers(perPage: Int, since: Int) : Flow<Resource<List<AllUser>>>
}