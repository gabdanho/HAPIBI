package com.gabdanho.hapibi.data.remote.model.ai

/**
 * Ответ AI сервиса.
 *
 * @property choices Список вариантов ответа.
 */
data class CompletionResponse(
    val choices: List<Choice> = emptyList(),
)