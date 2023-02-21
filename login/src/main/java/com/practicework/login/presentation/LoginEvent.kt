package com.practicework.login.presentation

sealed interface LoginEvent {
    object SignIn : LoginEvent
    object GetToken : LoginEvent
    object CloseError : LoginEvent
    object NavigateSignedIn : LoginEvent
    class InputChange(val input: String) : LoginEvent
}
