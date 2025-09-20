package com.gabdanho.hapibi.domain.usecase

import com.gabdanho.hapibi.domain.interfaces.repository.remote.SocialNetworkRepository
import com.gabdanho.hapibi.domain.interfaces.usecase.LogoutUseCase
import com.gabdanho.hapibi.domain.model.ApiResult
import javax.inject.Inject

class LogoutUseCaseImpl @Inject constructor(
    private val socialNetworkRepository: SocialNetworkRepository
) : LogoutUseCase {

    override suspend fun invoke(): ApiResult<Boolean> {
        return socialNetworkRepository.logout()
    }
}