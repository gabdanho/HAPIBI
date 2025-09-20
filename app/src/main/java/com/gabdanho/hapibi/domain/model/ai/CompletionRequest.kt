package com.gabdanho.hapibi.domain.model.ai

data class CompletionRequest(
    val messages: List<Message> = emptyList(),
)