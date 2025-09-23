package com.gabdanho.hapibi.presentation.screens.login

import com.gabdanho.hapibi.presentation.model.UiMessage

/**
 * UI-состояние экрана авторизации.
 *
 * @property uiMessage Сообщение для отображения пользователю.
 */
data class LoginScreenUiState(
    val uiMessage: UiMessage? = null,
)