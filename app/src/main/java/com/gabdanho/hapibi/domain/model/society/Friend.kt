package com.gabdanho.hapibi.domain.model.society

/**
 * Доменная модель друга.
 *
 * @property id Уникальный идентификатор пользователя.
 * @property birthDayDate Дата рождения.
 * @property firstName Имя.
 * @property lastName Фамилия.
 * @property imageUrl Ссылка на аватарку.
 */
data class Friend(
    val id: Int = 0,
    val birthDayDate: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val imageUrl: String = ""
)