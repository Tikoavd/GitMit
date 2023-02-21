package com.practicework.repos.presentation

import com.practicework.repos.domain.models.Repo

data class ReposState(
    val repos: List<Repo> = listOf(),
    val isLoading: Boolean = false,
    val isUpdating: Boolean = false
)