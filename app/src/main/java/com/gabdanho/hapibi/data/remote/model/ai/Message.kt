package com.gabdanho.hapibi.data.remote.model.ai

/**
 * Сообщение для AI модели.
 *
 * @property role Роль участника диалога (например: "system", "user", "assistant").
 * @property content Текстовое содержимое сообщения.
 */
data class Message(
    val role: String = "",
    val content: String = "",
)