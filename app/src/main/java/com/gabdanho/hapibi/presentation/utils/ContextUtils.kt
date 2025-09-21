package com.gabdanho.hapibi.presentation.utils

import android.content.Context
import android.widget.Toast
import com.gabdanho.hapibi.presentation.constants.TOAST_DURATION
import com.gabdanho.hapibi.presentation.mappers.resources.StringToResourceIdMapperImpl
import com.gabdanho.hapibi.presentation.model.UiMessage
import kotlinx.coroutines.delay

/**
 * Отображает [UiMessage] через Toast.
 *
 * @receiver Контекст, используемый для отображения Toast
 * @param uiMessage Сообщение для отображения
 * @param clearMessage Функция для очистки сообщения после показа
 */
suspend fun Context.showUiMessage(uiMessage: UiMessage, clearMessage: () -> Unit) {
    if (uiMessage.textResName != null) {
        val resId = StringToResourceIdMapperImpl().map(uiMessage.textResName)
        Toast.makeText(this, this.getString(resId), Toast.LENGTH_SHORT).show()
        delay(TOAST_DURATION)
    }
    if (uiMessage.details.isNotBlank()) {
        Toast.makeText(this, uiMessage.details, Toast.LENGTH_SHORT).show()
    }
}