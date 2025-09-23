package com.gabdanho.hapibi.data.remote.api

import com.gabdanho.hapibi.data.remote.model.vk.VkResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API для взаимодействия с VK.
 */
interface VkApiService {

    /**
     * Получает список друзей пользователя VK.
     *
     * @param accessToken Токен доступа.
     * @param userId Идентификатор пользователя (опционально).
     * @param fields Поля, которые нужно запросить (по умолчанию дата рождения, имя, фамилия, фото).
     * @param apiVersion Версия API VK (по умолчанию 5.199).
     * @return Ответ API [VkResponse] со списком друзей.
     */
    @GET("method/friends.get")
    suspend fun getUserInfo(
        @Query("access_token") accessToken: String,
        @Query("user_id") userId: Int? = null,
        @Query("fields") fields: String = "bdate,first_name,last_name,photo_400_orig",
        @Query("v") apiVersion: String = "5.199"
    ): VkResponse
}