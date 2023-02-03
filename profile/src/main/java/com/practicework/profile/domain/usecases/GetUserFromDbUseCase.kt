package com.practicework.profile.domain.usecases

import com.practicework.profile.domain.ProfileRepository

class GetUserFromDbUseCase(private val profileRepository: ProfileRepository) {

    operator fun invoke() = profileRepository.getUserFromDb()
}