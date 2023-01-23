package com.practicework.login.presentation

data class LoginState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isSignedIn: Boolean = false
)