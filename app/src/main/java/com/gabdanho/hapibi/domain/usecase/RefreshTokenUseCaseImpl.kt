package com.gabdanho.hapibi.domain.usecase

import com.gabdanho.hapibi.domain.interfaces.repository.remote.SocialNetworkRepository
import com.gabdanho.hapibi.domain.interfaces.usecase.RefreshTokenUseCase
import com.gabdanho.hapibi.domain.model.ApiResult
import javax.inject.Inject

class RefreshTokenUseCaseImpl @Inject constructor(
    private val socialNetworkRepository: SocialNetworkRepository
) : RefreshTokenUseCase {

    override suspend fun invoke(): ApiResult<String> {
        return socialNetworkRepository.refreshToken()
    }
}