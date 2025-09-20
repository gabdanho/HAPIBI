package com.gabdanho.hapibi.presentation.screens.friends

import com.gabdanho.hapibi.presentation.model.Friend

sealed class FriendsScreenEvent {

    data object Logout : FriendsScreenEvent()

    data object UpdateData : FriendsScreenEvent()

    data class OnFriendClick(val friend: Friend) : FriendsScreenEvent()

    data object ClearMessage : FriendsScreenEvent()
}