package com.gabdanho.hapibi.domain.usecase

import com.gabdanho.hapibi.domain.interfaces.repository.local.AccessTokenDataSource
import com.gabdanho.hapibi.domain.interfaces.usecase.GetAccessTokenUseCase
import javax.inject.Inject

class GetAccessTokenUseCaseImpl @Inject constructor(
    private val accessTokenDataSource: AccessTokenDataSource
) : GetAccessTokenUseCase {

    override fun invoke(): String? {
        return accessTokenDataSource.getAccessToken()
    }
}