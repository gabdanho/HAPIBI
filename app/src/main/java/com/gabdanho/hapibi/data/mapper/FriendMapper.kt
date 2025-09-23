package com.gabdanho.hapibi.data.mapper

import com.gabdanho.hapibi.data.remote.model.vk.Friend
import com.gabdanho.hapibi.domain.model.society.Friend as FriendDomain

/**
 * Преобразует доменную модель друга в сетевую модель.
 *
 * @receiver [FriendDomain] Доменная модель друга.
 * @return [Friend] Сетевая модель для API.
 */
fun FriendDomain.toData(): Friend {
    return Friend(
        id = id,
        bdate = birthDayDate,
        firstName = firstName,
        lastName = lastName,
        imageUrl = imageUrl
    )
}

/**
 * Преобразует сетевую модель друга в доменную модель.
 *
 * @receiver [Friend] Сетевая модель.
 * @return [FriendDomain] Доменная модель.
 */
fun Friend.toDomain(): FriendDomain {
    return FriendDomain(
        id = id,
        birthDayDate = bdate,
        firstName = firstName,
        lastName = lastName,
        imageUrl = imageUrl
    )
}