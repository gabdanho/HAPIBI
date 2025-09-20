package com.gabdanho.hapibi.domain.usecase

import com.gabdanho.hapibi.domain.interfaces.repository.local.AccessTokenDataSource
import com.gabdanho.hapibi.domain.interfaces.usecase.SetAccessTokenUseCase
import javax.inject.Inject

class SetAccessTokenUseCaseImpl @Inject constructor(
    private val accessTokenDataSource: AccessTokenDataSource
) : SetAccessTokenUseCase {

    override fun invoke(token: String) {
        accessTokenDataSource.setAccessToken(token = token)
    }
}