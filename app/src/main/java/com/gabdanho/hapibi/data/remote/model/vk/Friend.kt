package com.gabdanho.hapibi.data.remote.model.vk

import com.google.gson.annotations.SerializedName

/**
 * Модель друга из VK.
 *
 * @property id Уникальный идентификатор пользователя.
 * @property bdate Дата рождения.
 * @property trackCode Служебное поле VK.
 * @property firstName Имя.
 * @property lastName Фамилия.
 * @property canAccessClosed Флаг доступа к профилю.
 * @property isClosed Флаг закрытого профиля.
 * @property imageUrl Ссылка на аватарку.
 */
data class Friend(
    val id: Int = 0,
    val bdate: String = "",
    @SerializedName("track_code") val trackCode: String = "",
    @SerializedName("first_name") val firstName: String = "",
    @SerializedName("last_name") val lastName: String = "",
    @SerializedName("can_access_closed") val canAccessClosed: Boolean = false,
    @SerializedName("is_closed") val isClosed: Boolean = false,
    @SerializedName("photo_400_orig") val imageUrl: String = "",
)
