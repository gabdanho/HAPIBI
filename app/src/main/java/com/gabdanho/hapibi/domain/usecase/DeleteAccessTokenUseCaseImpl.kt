package com.gabdanho.hapibi.domain.usecase

import com.gabdanho.hapibi.domain.interfaces.repository.local.AccessTokenDataSource
import com.gabdanho.hapibi.domain.interfaces.usecase.DeleteAccessTokenUseCase
import javax.inject.Inject

class DeleteAccessTokenUseCaseImpl @Inject constructor(
    private val accessTokenDataSource: AccessTokenDataSource
) : DeleteAccessTokenUseCase {

    override fun invoke() {
        accessTokenDataSource.deleteAccessToken()
    }
}