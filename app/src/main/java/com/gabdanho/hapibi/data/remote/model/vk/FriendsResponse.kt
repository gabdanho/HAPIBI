package com.gabdanho.hapibi.data.remote.model.vk

data class FriendsResponse(
    val count: Int = 0,
    val items: List<Friend> = emptyList(),
)
