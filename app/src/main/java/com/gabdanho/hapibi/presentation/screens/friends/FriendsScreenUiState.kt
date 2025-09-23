package com.gabdanho.hapibi.presentation.screens.friends

import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.presentation.model.LoadingState
import com.gabdanho.hapibi.presentation.model.UiMessage

/**
 * UI-состояние экрана друзей.
 *
 * @property loadingState Состояние загрузки.
 * @property uiMessage Сообщение для отображения пользователю.
 * @property friends Список друзей.
 */
data class FriendsScreenUiState(
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
    val friends: List<Friend> = emptyList(),
)