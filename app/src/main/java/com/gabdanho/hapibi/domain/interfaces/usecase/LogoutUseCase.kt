package com.gabdanho.hapibi.domain.interfaces.usecase

import com.gabdanho.hapibi.domain.model.ApiResult

/**
 * UseCase для выхода из VK.
 */
interface LogoutUseCase {

    suspend operator fun invoke(): ApiResult<Boolean>
}