package com.practicework.all_users.data.source

import com.practicework.all_users.data.remote_data_source.models.AllUserApiModel
import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.room.models.AllUserEntity

object Mappers {

    fun mapAllUserApiModelToAllUser(allUserApiModel: AllUserApiModel) : AllUser =
        AllUser(
            login = allUserApiModel.login ?: AllUser.FIELD_UNKNOWN,
            avatarUrl = allUserApiModel.avatarUrl ?: AllUser.FIELD_UNKNOWN
        )

    fun mapAllUserApiModelListToAllUserList(list: List<AllUserApiModel>) : List<AllUser> =
        list.map { mapAllUserApiModelToAllUser(it) }

    fun mapAllUserEntityToAllUser(allUserEntity: AllUserEntity) : AllUser =
        AllUser(
            login = allUserEntity.login,
            avatarUrl = AllUser.FIELD_UNKNOWN
        )

    fun mapAllUserEntityListToAllUserList(list: List<AllUserEntity>) : List<AllUser> =
        list.map { mapAllUserEntityToAllUser(it) }

    fun mapAllUserToAllUserEntity(allUser: AllUser) : AllUserEntity =
        AllUserEntity(login = allUser.login)

    fun mapAllUserListToAllUserEntityList(list: List<AllUser>) : List<AllUserEntity> =
        list.map { mapAllUserToAllUserEntity(it) }
}