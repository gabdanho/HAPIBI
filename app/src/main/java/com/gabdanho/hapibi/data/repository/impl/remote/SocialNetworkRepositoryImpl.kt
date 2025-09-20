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

class SocialNetworkRepositoryImpl @Inject constructor(
    private val vkApiService: VkApiService,
    private val vkid: VKID,
) : SocialNetworkRepository {

    override suspend fun getFriends(accessToken: String): ApiResult<List<Friend>> {
        return safeApiCall {
            vkApiService.getUserInfo(accessToken = accessToken).response.items.map { friend -> friend.toDomain() }
        }
    }

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

    override suspend fun refreshToken(): ApiResult<String> {
        return safeApiCall {
            val deferred = CompletableDeferred<String>()

            vkid.refreshToken(
                callback = object : VKIDRefreshTokenCallback {
                    override fun onSuccess(token: AccessToken) {
                        deferred.complete(token.token)
                    }

                    override fun onFail(fail: VKIDRefreshTokenFail) {
                        deferred.completeExceptionally(Exception(fail.description))
                    }
                }
            )

            deferred.await()
        }
    }
}