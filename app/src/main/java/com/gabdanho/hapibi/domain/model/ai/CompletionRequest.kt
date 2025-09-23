package com.gabdanho.hapibi.domain.model.ai

/**
 * Запрос для AI модели.
 *
 * @property messages Список сообщений, формирующих контекст диалога.
 */
data class CompletionRequest(
    val messages: List<Message> = emptyList(),
)