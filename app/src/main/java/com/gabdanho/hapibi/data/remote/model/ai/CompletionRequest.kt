package com.gabdanho.hapibi.data.remote.model.ai

data class CompletionRequest(
    val model: String = "",
    val messages: List<Message> = emptyList()
)