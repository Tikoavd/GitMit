package com.practicework.profile.presentation

import android.graphics.Bitmap
import com.practicework.profile.domain.models.User

data class ProfileState(
    val user: User = User("", 0, 0),
    val bitmap: Bitmap? = null,
    val isNightMode: Boolean? = null
)
