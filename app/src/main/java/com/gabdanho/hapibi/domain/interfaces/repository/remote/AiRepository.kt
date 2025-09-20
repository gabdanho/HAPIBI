package com.gabdanho.hapibi.domain.interfaces.repository.remote

import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest

interface AiRepository {

    suspend fun sendPrompt(completionRequest: CompletionRequest): ApiResult<String>
}