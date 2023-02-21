package com.practicework.repos.domain.models

data class Repo(
    val name: String,
    val language: String,
    val visibility: String
) {
    companion object {
        const val UNKNOWN_FIELD_VALUE = "unknown"
    }
}
