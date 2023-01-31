package com.practicework.all_users.presentation

sealed class AllUsersEvent {
    object Update : AllUsersEvent()
    object GetMore : AllUsersEvent()
}
