package com.gabdanho.hapibi.data.mapper

import com.gabdanho.hapibi.data.remote.model.vk.Friend
import com.gabdanho.hapibi.domain.model.society.Friend as FriendDomain

fun FriendDomain.toData(): Friend {
    return Friend(
        id = id,
        bdate = birthDayDate,
        firstName = firstName,
        lastName = lastName,
        imageUrl = imageUrl
    )
}

fun Friend.toDomain(): FriendDomain {
    return FriendDomain(
        id = id,
        birthDayDate = bdate,
        firstName = firstName,
        lastName = lastName,
        imageUrl = imageUrl
    )
}