package com.gabdanho.hapibi.domain.usecase

import com.gabdanho.hapibi.domain.interfaces.repository.remote.AiRepository
import com.gabdanho.hapibi.domain.interfaces.usecase.SendPromptUseCase
import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest
import javax.inject.Inject

class SendPromptUseCaseImpl @Inject constructor(
    private val aiRepository: AiRepository
) : SendPromptUseCase {

    override suspend fun invoke(completionRequest: CompletionRequest): ApiResult<String> {
        return aiRepository.sendPrompt(completionRequest = completionRequest)
    }
}