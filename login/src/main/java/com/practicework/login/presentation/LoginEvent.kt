package com.practicework.login.presentation

sealed class LoginEvent {
    object SignIn : LoginEvent()
    object GetToken : LoginEvent()
    object CloseError : LoginEvent()
    class InputChange(val input: String) : LoginEvent()
}
