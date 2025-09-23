package com.gabdanho.hapibi.data.repository.impl.remote

import com.gabdanho.hapibi.data.mapper.toDomain
import com.gabdanho.hapibi.data.remote.api.VkApiService
import com.gabdanho.hapibi.data.remote.model.safeApiCall
import com.gabdanho.hapibi.domain.interfaces.repository.remote.SocialNetworkRepository
import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.society.Friend
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.logout.VKIDLogoutCallback
import com.vk.id.logout.VKIDLogoutFail
import com.vk.id.refresh.VKIDRefreshTokenCallback
import com.vk.id.refresh.VKIDRefreshTokenFail
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

/**
 * Репозиторий для работы с социальной сетью VK.
 *
 * @property vkApiService API-сервис для выполнения сетевых запросов к VK.
 * @property vkid SDK для работы с VKID (аутентификация, управление сессиями).
 */
class SocialNetworkRepositoryImpl @Inject constructor(
    private val vkApiService: VkApiService,
    private val vkid: VKID,
) : SocialNetworkRepository {

    /**
     * Получает список друзей текущего пользователя.
     *
     * @param accessToken Токен доступа VK.
     * @return Результат выполнения [ApiResult] со списком друзей.
     */
    override suspend fun getFriends(accessToken: String): ApiResult<List<Friend>> {
        return safeApiCall {
            vkApiService.getUserInfo(accessToken = accessToken).response.items.map { friend -> friend.toDomain() }
        }
    }

    /**
     * Выполняет выход пользователя из VKID.
     *
     * @return Результат выполнения [ApiResult], возвращающий `true` при успехе.
     */
    override suspend fun logout(): ApiResult<Boolean> {
        return safeApiCall {
            val deferred = CompletableDeferred<Boolean>()

            vkid.logout(
                callback = object : VKIDLogoutCallback {
                    override fun onSuccess() {
                        deferred.complete(true)
                    }
                    override fun onFail(fail: VKIDLogoutFail) {
                        deferred.completeExceptionally(Exception(fail.description))
                    }
                }
            )

            deferred.await()
        }
    }

    /**
     * Обновляет access token через VKID.
     *
     * @return Результат выполнения [ApiResult] со строкой токена или `null`, если обновление не удалось.
     */
    override suspend fun refreshToken(): ApiResult<String?> {
        return safeApiCall {
            val deferred = CompletableDeferred<String?>()

            vkid.refreshToken(
                callback = object : VKIDRefreshTokenCallback {
                    override fun onSuccess(token: AccessToken) {
                        deferred.complete(token.token)
                    }

                    override fun onFail(fail: VKIDRefreshTokenFail) {
                        deferred.complete(null)
                    }
                }
            )

            deferred.await()
        }
    }
}