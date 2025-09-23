package com.gabdanho.hapibi.presentation.screens.congratulation

import com.gabdanho.hapibi.presentation.model.LoadingState
import com.gabdanho.hapibi.presentation.model.UiMessage

/**
 * UI-состояние экрана поздравления.
 *
 * @property uiMessage Сообщение для отображения пользователю.
 * @property personName Имя человека, которому создается поздравление.
 * @property finishedCongratulation Сгенерированное поздравление.
 * @property holidayInput Введенное название праздника.
 * @property personStatusInput Введенный статус человека.
 * @property styleInput Стиль поздравления.
 * @property importantWordsInput Важные слова для поздравления.
 * @property fixProblemsInput Введенные исправления.
 * @property loadingState Состояние загрузки.
 * @property isGenerateButtonEnabled Активна ли кнопка генерации.
 * @property isFixButtonEnabled Активна ли кнопка исправления.
 */
data class CongratulationScreenUiState(
    val uiMessage: UiMessage? = null,
    val personName: String = "",
    val finishedCongratulation: String = "",
    val holidayInput: String = "",
    val personStatusInput: String = "",
    val styleInput: String = "",
    val importantWordsInput: String = "",
    val fixProblemsInput: String = "",
    val loadingState: LoadingState? = null,
    val isGenerateButtonEnabled: Boolean = false,
    val isFixButtonEnabled: Boolean = false,
)