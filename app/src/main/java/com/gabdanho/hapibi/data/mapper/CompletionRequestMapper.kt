package com.gabdanho.hapibi.data.mapper

import com.gabdanho.hapibi.data.remote.model.ai.CompletionRequest
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest as CompletionRequestDomain

/**
 * Преобразует доменную модель запроса в модель для сетевого слоя.
 *
 * @receiver [CompletionRequestDomain] Доменная модель запроса.
 * @return [CompletionRequest] Модель для передачи в API.
 */
fun CompletionRequestDomain.toData(): CompletionRequest {
    return CompletionRequest(
        messages = messages.map { it.toData() }
    )
}

/**
 * Преобразует сетевую модель запроса в доменную модель.
 *
 * @receiver [CompletionRequest] Модель из API.
 * @return [CompletionRequestDomain] Доменная модель запроса.
 */
fun CompletionRequest.toDomain(): CompletionRequestDomain {
    return CompletionRequestDomain(
        messages = messages.map { it.toDomain() }
    )
}