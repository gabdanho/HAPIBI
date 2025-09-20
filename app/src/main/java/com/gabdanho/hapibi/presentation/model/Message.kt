package com.gabdanho.hapibi.presentation.model

data class Message(
    val role: AiRole? = null,
    val content: String = "",
)