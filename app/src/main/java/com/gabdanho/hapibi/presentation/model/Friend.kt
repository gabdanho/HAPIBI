package com.gabdanho.hapibi.presentation.model

import kotlinx.serialization.Serializable

/**
 * Модель друга в presentation-слое.
 *
 * @property id Уникальный идентификатор пользователя.
 * @property imageUrl Ссылка на фото профиля.
 * @property firstName Имя.
 * @property lastName Фамилия.
 * @property birthDayDate Дата рождения (отформатированная для отображения).
 */
@Serializable
data class Friend(
    val id: Int = 0,
    val imageUrl: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val birthDayDate: String = ""
)
