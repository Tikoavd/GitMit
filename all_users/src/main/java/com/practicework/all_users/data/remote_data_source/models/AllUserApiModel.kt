package com.practicework.all_users.data.remote_data_source.models

import com.google.gson.annotations.SerializedName

data class AllUserApiModel(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
