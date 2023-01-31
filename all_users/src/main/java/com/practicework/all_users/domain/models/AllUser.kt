package com.practicework.all_users.domain.models

data class AllUser(
    val login: String,
    val avatarUrl: String
) {
    companion object {
        const val AVATAR_UNKNOWN = "unknown"
    }
}
