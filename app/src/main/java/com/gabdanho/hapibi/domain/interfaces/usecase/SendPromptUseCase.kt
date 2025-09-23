package com.gabdanho.hapibi.domain.interfaces.usecase

import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest

/**
 * UseCase для отправки промпта в AI.
 */
interface SendPromptUseCase {

    suspend operator fun invoke(completionRequest: CompletionRequest): ApiResult<String>
}