package com.gabdanho.hapibi.presentation.model

data class CompletionRequest(
    val messages: List<Message> = emptyList(),
)