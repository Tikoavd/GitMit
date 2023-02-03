package com.practicework.profile.domain.usecases

import com.practicework.profile.domain.ProfileRepository

class ClearAllDataUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke() = profileRepository.clearAllData()
}