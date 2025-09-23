package com.gabdanho.hapibi.presentation.screens.congratulation

/**
 * События для [CongratulationScreenViewModel].
 */
sealed class CongratulationScreenEvent {

    /** Генерация поздравления. */
    data object GenerateCongratulation : CongratulationScreenEvent()

    /** Исправление сгенерированного поздравления. */
    data object FixCongratulation: CongratulationScreenEvent()

    /** Очистка сообщения UI. */
    data object ClearMessage : CongratulationScreenEvent()

    /** Инициализация имени человека. */
    data class InitPersonName(val name: String) : CongratulationScreenEvent()

    /** Обновление поля "праздник". */
    data class UpdateHolidayInput(val value: String) : CongratulationScreenEvent()

    /** Обновление поля "статус человека". */
    data class UpdatePersonStatusInput(val value: String) : CongratulationScreenEvent()

    /** Обновление поля "важные слова". */
    data class UpdateImportantWordsInput(val value: String) : CongratulationScreenEvent()

    /** Обновление поля "стиль поздравления". */
    data class UpdateStyleInput(val value: String) : CongratulationScreenEvent()

    /** Обновление поля "исправить проблемы". */
    data class UpdateFixProblemsInput(val value: String) : CongratulationScreenEvent()
}