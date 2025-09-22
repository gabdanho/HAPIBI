package com.gabdanho.hapibi.data.remote.api

import com.gabdanho.hapibi.data.remote.model.ai.CompletionRequest
import com.gabdanho.hapibi.data.remote.model.ai.CompletionResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AiService {

    @POST("chat/completions")
    suspend fun generateCongrat(
        @Body request: CompletionRequest
    ): CompletionResponse
}