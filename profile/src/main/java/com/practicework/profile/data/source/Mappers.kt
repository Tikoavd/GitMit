package com.practicework.profile.data.source

import com.practicework.core.room.models.UserDbModel
import com.practicework.profile.domain.models.User

object Mappers {

    fun mapUserDbModelToUser(userDbModel: UserDbModel) : User =
        User(
            login = userDbModel.login,
            followingCount = userDbModel.followingCount,
            followersCount = userDbModel.followersCount
        )
}