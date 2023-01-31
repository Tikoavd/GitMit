package com.practicework.all_users.data.source

import com.practicework.all_users.data.remote_data_source.models.AllUserApiModel
import com.practicework.all_users.domain.models.AllUser
import com.practicework.core.room.models.AllUserDbModel

object Mappers {

    fun mapAllUserApiModelToAllUser(allUserApiModel: AllUserApiModel) : AllUser =
        AllUser(
            login = allUserApiModel.login,
            avatarUrl = allUserApiModel.avatarUrl
        )

    fun mapAllUserApiModelListToAllUserList(list: List<AllUserApiModel>) : List<AllUser> =
        list.map { mapAllUserApiModelToAllUser(it) }

    fun mapAllUserDbModelToAllUser(allUserDbModel: AllUserDbModel) : AllUser =
        AllUser(
            login = allUserDbModel.login,
            avatarUrl = AllUser.AVATAR_UNKNOWN
        )

    fun mapAllUserDbModelListToAllUserList(list: List<AllUserDbModel>) : List<AllUser> =
        list.map { mapAllUserDbModelToAllUser(it) }

    fun mapAllUserToAllUserDbModel(allUser: AllUser) : AllUserDbModel =
        AllUserDbModel(login = allUser.login)

    fun mapAllUserListToAllUserDbModelList(list: List<AllUser>) : List<AllUserDbModel> =
        list.map { mapAllUserToAllUserDbModel(it) }
}