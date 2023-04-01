package com.practicework.all_users.presentation

sealed interface AllUsersEvent {
    object Update : AllUsersEvent
    object GetMore : AllUsersEvent
}
