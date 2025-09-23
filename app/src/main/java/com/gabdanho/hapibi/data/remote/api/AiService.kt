package com.gabdanho.hapibi.data.remote.api

import com.gabdanho.hapibi.data.remote.model.ai.CompletionRequest
import com.gabdanho.hapibi.data.remote.model.ai.CompletionResponse
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * API для взаимодействия с AI сервисом.
 */
interface AiService {

    /**
     * Отправляет запрос для генерации ответа от AI.
     *
     * @param request Объект запроса, содержащий список сообщений.
     * @return Ответ AI в виде [CompletionResponse].
     */
    @POST("chat/completions")
    suspend fun generateCongrat(
        @Body request: CompletionRequest
    ): CompletionResponse
}