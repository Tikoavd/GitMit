package com.practicework.repos.presentation

sealed interface ReposEvent {
    object UpdateRepos : ReposEvent
    object GetMoreRepos : ReposEvent
}