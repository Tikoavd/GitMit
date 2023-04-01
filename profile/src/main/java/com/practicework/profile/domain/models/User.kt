package com.practicework.profile.domain.models

data class User(
    val login: String,
    val followersCount: Int,
    val followingCount: Int
)