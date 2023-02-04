package com.practicework.details.presentation

import com.practicework.details.domain.models.Repo
import com.practicework.details.domain.models.User

data class DetailsState(
    val user: User? = null,
    val repos: List<Repo> = listOf(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isUpdating: Boolean = false
)