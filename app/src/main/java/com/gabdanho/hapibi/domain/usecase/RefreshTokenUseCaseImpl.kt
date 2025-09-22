package com.gabdanho.hapibi.domain.usecase

import com.gabdanho.hapibi.domain.interfaces.repository.remote.SocialNetworkRepository
import com.gabdanho.hapibi.domain.interfaces.usecase.HasInternetConnectionUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.RefreshTokenUseCase
import com.gabdanho.hapibi.domain.model.ApiResult
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class RefreshTokenUseCaseImpl @Inject constructor(
    private val socialNetworkRepository: SocialNetworkRepository,
    private val hasInternetConnectionUseCase: HasInternetConnectionUseCase,
) : RefreshTokenUseCase {

    override suspend fun invoke(): ApiResult<String?> {
        val isOnline = hasInternetConnectionUseCase().firstOrNull() ?: false

        if (!isOnline) return ApiResult.Error.ConnectionError("NO_INTERNET")

        return socialNetworkRepository.refreshToken()
    }
}