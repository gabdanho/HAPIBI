package com.gabdanho.hapibi.domain.interfaces.repository.remote

import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest

/**
 * Репозиторий для взаимодействия с AI сервисом.
 */
interface AiRepository {

    /**
     * Отправляет промпт в AI модель.
     *
     * @param completionRequest Запрос с сообщениями.
     * @return Результат выполнения [ApiResult] со строкой ответа от AI.
     */
    suspend fun sendPrompt(completionRequest: CompletionRequest): ApiResult<String>
}