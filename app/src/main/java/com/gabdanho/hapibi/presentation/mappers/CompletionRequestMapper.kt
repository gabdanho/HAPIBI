package com.gabdanho.hapibi.presentation.mappers

import com.gabdanho.hapibi.presentation.model.CompletionRequest
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest as CompletionRequestDomain

fun CompletionRequestDomain.toPresentation(): CompletionRequest {
    return CompletionRequest(
        messages = messages.map { it.toPresentation() }
    )
}

fun CompletionRequest.toDomain(): CompletionRequestDomain {
    return CompletionRequestDomain(
        messages = messages.map { it.toDomain() }
    )
}