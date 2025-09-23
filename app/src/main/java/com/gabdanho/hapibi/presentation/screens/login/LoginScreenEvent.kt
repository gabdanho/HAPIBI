package com.gabdanho.hapibi.presentation.screens.login

/**
 * События для [LoginScreenViewModel].
 */
sealed class LoginScreenEvent {

    /** Выполнение логина с токеном. */
    data class OnLogin(val token: String) : LoginScreenEvent()

    /** Обновление сообщения об ошибке. */
    data class UpdateErrorMessage(val details: String) : LoginScreenEvent()

    /** Очистка сообщения UI. */
    data object ClearMessage : LoginScreenEvent()
}