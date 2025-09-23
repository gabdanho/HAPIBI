package com.gabdanho.hapibi.data.remote.model.ai

/**
 * Один из вариантов ответа AI.
 *
 * @property message Сгенерированное сообщение.
 */
data class Choice(
    val message: Message = Message(),
)