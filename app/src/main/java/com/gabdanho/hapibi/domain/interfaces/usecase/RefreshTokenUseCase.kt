package com.gabdanho.hapibi.domain.interfaces.usecase

import com.gabdanho.hapibi.domain.model.ApiResult

interface RefreshTokenUseCase {

    suspend operator fun invoke(): ApiResult<String?>
}