package com.practicework.repos.data.remote_data_source.models

import com.google.gson.annotations.SerializedName

data class RepoApiModel(
    @SerializedName(RepoApiModelConfigs.NAME)
    val name: String?,
    @SerializedName(RepoApiModelConfigs.LANGUAGE)
    val language: String?,
    @SerializedName(RepoApiModelConfigs.VISIBILITY)
    val visibility: String?
)
