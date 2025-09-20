package com.gabdanho.hapibi.data.repository.impl.remote

import com.gabdanho.hapibi.data.mapper.toData
import com.gabdanho.hapibi.data.remote.api.AiService
import com.gabdanho.hapibi.data.remote.model.safeApiCall
import com.gabdanho.hapibi.domain.interfaces.repository.remote.AiRepository
import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest as CompletionRequestDomain
import javax.inject.Inject

class AiRepositoryImpl @Inject constructor(
    private val aiService: AiService
) : AiRepository {

    override suspend fun sendPrompt(completionRequest: CompletionRequestDomain): ApiResult<String> {
        return safeApiCall {
            var completionRequestData = completionRequest.toData()
            completionRequestData = completionRequestData.copy(model = AI_MODEL)

            val aiMessageResponse = aiService.generateCongrat(
                request = completionRequestData
            ).choices.first().message.content

            aiMessageResponse
        }
    }

    companion object {
        private const val AI_MODEL = "gpt-4o-mini"
    }
}