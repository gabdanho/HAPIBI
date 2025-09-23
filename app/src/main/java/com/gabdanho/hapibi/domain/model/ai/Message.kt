package com.gabdanho.hapibi.domain.model.ai

/**
 * Сообщение в рамках диалога.
 *
 * @property role Роль участника диалога (например: "system", "user", "assistant").
 * @property content Текст сообщения.
 */
data class Message(
    val role: String,
    val content: String
)