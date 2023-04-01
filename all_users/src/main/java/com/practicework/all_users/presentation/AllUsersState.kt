package com.practicework.all_users.presentation

import com.practicework.all_users.domain.models.AllUser

data class AllUsersState(
    val userList: List<AllUser> = listOf(),
    val isLoading: Boolean = false,
    val isUpdating: Boolean = false
)
