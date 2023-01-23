package com.practicework.login.domain.models

data class User(
    val login: String,
    val avatarUrl: String,
    val followersCount: Int,
    val followingCount: Int
) {
    companion object {
        const val NO_IMAGE = "unknown image"
    }
}