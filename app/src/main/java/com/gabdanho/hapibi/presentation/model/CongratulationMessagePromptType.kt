package com.gabdanho.hapibi.presentation.model

/**
 * Типы промптов для генерации поздравлений.
 */
enum class CongratulationMessagePromptType {
    /** Сгенерировать новое поздравление. */
    CREATE_CONGRATULATION,
    /** Исправить существующее поздравление. */
    FIX_CONGRATULATION
}