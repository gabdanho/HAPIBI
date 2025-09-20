package com.gabdanho.hapibi.presentation.mappers

import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.domain.model.society.Friend as FriendDomain
import com.gabdanho.hapibi.presentation.utils.convertDate

fun FriendDomain.toPresentation(): Friend {
    return Friend(
        id = id,
        firstName = firstName,
        lastName = lastName,
        birthDayDate = convertDate(birthDayDate),
        imageUrl = imageUrl
    )
}

fun Friend.toDomain(): FriendDomain {
    return FriendDomain(
        id = id,
        firstName = firstName,
        lastName = lastName,
        birthDayDate = birthDayDate,
        imageUrl = imageUrl
    )
}