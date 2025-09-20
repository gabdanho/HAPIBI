package com.gabdanho.hapibi.data.mapper

import com.gabdanho.hapibi.data.remote.model.ai.CompletionRequest
import com.gabdanho.hapibi.domain.model.ai.CompletionRequest as CompletionRequestDomain

fun CompletionRequestDomain.toData(): CompletionRequest {
    return CompletionRequest(
        messages = messages.map { it.toData() }
    )
}

fun CompletionRequest.toDomain(): CompletionRequestDomain {
    return CompletionRequestDomain(
        messages = messages.map { it.toDomain() }
    )
}