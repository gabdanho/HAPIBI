package com.gabdanho.hapibi.domain.interfaces.usecase

import com.gabdanho.hapibi.domain.model.ApiResult

interface LogoutUseCase {

    suspend operator fun invoke(): ApiResult<Boolean>
}