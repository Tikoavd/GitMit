package com.practicework.login.data.source

import com.practicework.core.room.models.UserDbModel
import com.practicework.login.data.remote_data_source.models.UserApiModel
import com.practicework.login.domain.models.User

object Mappers {
    fun mapUserApiModelToUser(userApiModel: UserApiModel): User =
        User(
            login = userApiModel.login,
            avatarUrl = userApiModel.avatarUrl,
            followersCount = userApiModel.followersCount,
            followingCount = userApiModel.followingCount
        )

    fun mapUserDbModelToUser(userDbModel: UserDbModel) : User =
        User(
            login = userDbModel.login,
            avatarUrl = User.NO_IMAGE,
            followersCount = userDbModel.followersCount,
            followingCount = userDbModel.followingCount
        )

    fun mapUserToUserDbModel(user: User) : UserDbModel =
        UserDbModel(
            login = user.login,
            followersCount = user.followersCount,
            followingCount = user.followingCount
        )
}