package com.gabdanho.hapibi.presentation.model

/**
 * Сообщение в диалоге с AI (presentation-слой).
 *
 * @property role Роль участника диалога (система, пользователь).
 * @property content Текст сообщения.
 */
data class Message(
    val role: AiRole? = null,
    val content: String = "",
)