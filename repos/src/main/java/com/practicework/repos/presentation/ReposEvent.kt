package com.practicework.repos.presentation

sealed class ReposEvent {
    object UpdateRepos : ReposEvent()
    object GetMoreRepos : ReposEvent()
}