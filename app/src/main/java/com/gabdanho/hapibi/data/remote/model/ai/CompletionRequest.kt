package com.gabdanho.hapibi.data.remote.model.ai

/**
 * Запрос к AI модели.
 *
 * @property model Название модели, которая будет использоваться.
 * @property messages Список сообщений (контекст диалога).
 */
data class CompletionRequest(
    val model: String = "",
    val messages: List<Message> = emptyList()
)