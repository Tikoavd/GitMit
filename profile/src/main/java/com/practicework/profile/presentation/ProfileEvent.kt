package com.practicework.profile.presentation

import android.net.Uri

sealed interface ProfileEvent {
    object SignOut : ProfileEvent
    class SaveUri(val uri: Uri) : ProfileEvent
    class SaveNightMode(val isNightMode: Boolean) : ProfileEvent
}
