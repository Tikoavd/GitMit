package com.practicework.profile.data.source

import com.practicework.core.room.models.UserEntity
import com.practicework.profile.domain.models.User

object Mappers {

    fun mapUserDbModelToUser(userEntity: UserEntity): User =
        User(
            login = userEntity.login,
            followingCount = userEntity.followingCount,
            followersCount = userEntity.followersCount
        )
}