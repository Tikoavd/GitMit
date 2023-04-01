package com.practicework.login.data.source

import com.practicework.core.room.models.UserEntity
import com.practicework.login.data.remote_data_source.models.UserApiModel

object Mappers {
    fun mapUserApiModelToUserEntity(userApiModel: UserApiModel): UserEntity =
        UserEntity(
            login = userApiModel.login ?: UserEntity.NO_LOGIN,
            followersCount = userApiModel.followersCount ?: UserEntity.NO_FOLLOWS,
            followingCount = userApiModel.followingCount ?: UserEntity.NO_FOLLOWS
        )
}