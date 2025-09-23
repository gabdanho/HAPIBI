package com.gabdanho.hapibi.presentation.mappers

import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.domain.model.society.Friend as FriendDomain
import com.gabdanho.hapibi.presentation.utils.convertDate

/**
 * Преобразует [FriendDomain] в [Friend].
 *
 * Выполняется форматирование даты рождения через [convertDate].
 *
 * @return [Friend] для presentation-слоя.
 */
fun FriendDomain.toPresentation(): Friend {
    return Friend(
        id = id,
        firstName = firstName,
        lastName = lastName,
        birthDayDate = convertDate(birthDayDate),
        imageUrl = imageUrl
    )
}

/**
 * Преобразует [Friend] в [FriendDomain].
 *
 * Используется для передачи данных из presentation в domain-слой.
 *
 * @return [FriendDomain].
 */
fun Friend.toDomain(): FriendDomain {
    return FriendDomain(
        id = id,
        firstName = firstName,
        lastName = lastName,
        birthDayDate = birthDayDate,
        imageUrl = imageUrl
    )
}