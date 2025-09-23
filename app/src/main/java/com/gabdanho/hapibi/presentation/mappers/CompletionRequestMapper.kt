package com.gabdanho.hapibi.presentation.mappers

import com.gabdanho.hapibi.presentation.model.CompletionRequest
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest as CompletionRequestDomain

/**
 * Преобразует [CompletionRequestDomain] в [CompletionRequest].
 *
 * Используется для маппинга domain-модели в presentation-слой.
 *
 * @return [CompletionRequest] с преобразованными сообщениями.
 */
fun CompletionRequestDomain.toPresentation(): CompletionRequest {
    return CompletionRequest(
        messages = messages.map { it.toPresentation() }
    )
}

/**
 * Преобразует [CompletionRequest] в [CompletionRequestDomain].
 *
 * Используется для маппинга presentation-модели в domain-слой.
 *
 * @return [CompletionRequestDomain] с преобразованными сообщениями.
 */
fun CompletionRequest.toDomain(): CompletionRequestDomain {
    return CompletionRequestDomain(
        messages = messages.map { it.toDomain() }
    )
}