package com.gabdanho.hapibi.domain.interfaces.usecase

import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest

interface SendPromptUseCase {

    suspend operator fun invoke(completionRequest: CompletionRequest): ApiResult<String>
}