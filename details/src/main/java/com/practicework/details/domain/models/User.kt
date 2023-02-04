package com.practicework.details.domain.models

data class User(
    val login: String,
    val avatarUrl: String,
    val followersCount: Int,
    val followingCount: Int
)
