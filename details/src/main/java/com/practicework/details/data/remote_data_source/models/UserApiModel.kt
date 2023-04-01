package com.practicework.details.data.remote_data_source.models

import com.google.gson.annotations.SerializedName

data class UserApiModel(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("followers")
    val followersCount: Int,
    @SerializedName("following")
    val followingCount: Int
)
