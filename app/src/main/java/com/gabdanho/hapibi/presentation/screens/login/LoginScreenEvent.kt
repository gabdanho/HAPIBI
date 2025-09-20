package com.gabdanho.hapibi.presentation.screens.login

sealed class LoginScreenEvent {

    data class OnLogin(val token: String) : LoginScreenEvent()

    data class UpdateErrorMessage(val details: String) : LoginScreenEvent()

    data object ClearMessage : LoginScreenEvent()
}