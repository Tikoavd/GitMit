package com.practicework.details.presentation

sealed class DetailsEvent {
    object Update : DetailsEvent()
    object GetMoreRepos : DetailsEvent()
}
