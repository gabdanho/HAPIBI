package com.gabdanho.hapibi.data.remote.repository

import retrofit2.http.Body
import retrofit2.http.POST

interface AimlapiService {
    @POST("/v1/chat/completions")
    suspend fun generateCongrat(
        @Body request: CompletionRequest
    ): CompletionResponse
}

data class CompletionRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)

data class CompletionResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: ContentMessage
)

data class ContentMessage(
    val role: String,
    val content: String
)