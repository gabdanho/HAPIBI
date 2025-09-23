package com.gabdanho.hapibi.data.remote.model.vk

/**
 * Ответ API при запросе друзей из VK.
 *
 * @property response Содержит список друзей и их количество.
 */
data class VkResponse(
    val response: FriendsResponse = FriendsResponse()
)
