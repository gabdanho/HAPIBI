package com.gabdanho.hapibi.presentation.screens.friends

import com.gabdanho.hapibi.presentation.model.Friend

/**
 * События для [FriendsScreenViewModel].
 */
sealed class FriendsScreenEvent {

    /** Выполнить выход из аккаунта. */
    data object Logout : FriendsScreenEvent()

    /** Обновление данных друзей. */
    data object UpdateData : FriendsScreenEvent()

    /** Нажатие на друга. */
    data class OnFriendClick(val friend: Friend) : FriendsScreenEvent()

    /** Очистка сообщения UI. */
    data object ClearMessage : FriendsScreenEvent()
}