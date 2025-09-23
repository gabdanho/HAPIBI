package com.gabdanho.hapibi.domain.interfaces.repository.remote

import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.society.Friend

/**
 * Репозиторий для работы с социальной сетью VK.
 */
interface SocialNetworkRepository {

    /**
     * Получает список друзей пользователя.
     *
     * @param accessToken Токен доступа VK.
     * @return Результат выполнения [ApiResult] со списком друзей.
     */
    suspend fun getFriends(accessToken: String): ApiResult<List<Friend>>

    /**
     * Выполняет выход пользователя из VK.
     *
     * @return Результат выполнения [ApiResult], где `true` — успешный выход.
     */
    suspend fun logout(): ApiResult<Boolean>

    /**
     * Обновляет access token.
     *
     * @return Результат выполнения [ApiResult] с новым токеном или `null`, если обновление не удалось.
     */
    suspend fun refreshToken(): ApiResult<String?>
}