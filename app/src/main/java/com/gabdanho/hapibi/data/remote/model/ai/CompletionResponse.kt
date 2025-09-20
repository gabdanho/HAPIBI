package com.gabdanho.hapibi.data.remote.model.ai

data class CompletionResponse(
    val choices: List<Choice> = emptyList(),
)