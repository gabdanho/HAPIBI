package com.gabdanho.hapibi.domain.interfaces.repository.remote

import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.society.Friend

interface SocialNetworkRepository {

    suspend fun getFriends(accessToken: String): ApiResult<List<Friend>>

    suspend fun logout(): ApiResult<Boolean>

    suspend fun refreshToken(): ApiResult<String?>
}