package com.gabdanho.hapibi.data.remote.model.vk

/**
 * Объект, содержащий список друзей VK и их общее количество.
 *
 * @property count Количество друзей.
 * @property items Список друзей.
 */
data class FriendsResponse(
    val count: Int = 0,
    val items: List<Friend> = emptyList(),
)
