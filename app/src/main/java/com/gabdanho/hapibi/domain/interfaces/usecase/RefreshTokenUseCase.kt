package com.gabdanho.hapibi.domain.interfaces.usecase

import com.gabdanho.hapibi.domain.model.ApiResult

/**
 * UseCase для обновления access token.
 */
interface RefreshTokenUseCase {

    suspend operator fun invoke(): ApiResult<String?>
}