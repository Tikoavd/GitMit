package com.practicework.login.data.remote_data_source.models

import com.google.gson.annotations.SerializedName
import com.practicework.login.data.remote_data_source.retrofit.ApiConfig

data class UserApiModel (
    @SerializedName(ApiConfig.LOGIN_API_NAME)
    val login: String?,
    @SerializedName(ApiConfig.FOLLOWERS_API_NAME)
    val followersCount: Int?,
    @SerializedName(ApiConfig.FOLLOWING_API_NAME)
    val followingCount: Int?
)