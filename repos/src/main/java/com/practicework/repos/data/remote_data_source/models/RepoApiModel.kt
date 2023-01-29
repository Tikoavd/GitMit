package com.practicework.repos.data.remote_data_source.models

import com.google.gson.annotations.SerializedName

data class RepoApiModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("language")
    val language: String?,
    @SerializedName("visibility")
    val visibility: String
)
