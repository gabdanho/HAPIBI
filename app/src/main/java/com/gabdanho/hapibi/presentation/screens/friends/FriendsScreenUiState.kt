package com.gabdanho.hapibi.presentation.screens.friends

import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.presentation.model.LoadingState
import com.gabdanho.hapibi.presentation.model.UiMessage

data class FriendsScreenUiState(
    val loadingState: LoadingState? = null,
    val uiMessage: UiMessage? = null,
    val friends: List<Friend> = emptyList(),
)