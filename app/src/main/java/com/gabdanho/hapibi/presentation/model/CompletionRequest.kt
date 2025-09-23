package com.gabdanho.hapibi.presentation.model

/**
 * Запрос к AI в presentation-слое.
 *
 * @property messages Список сообщений для модели.
 */
data class CompletionRequest(
    val messages: List<Message> = emptyList(),
)