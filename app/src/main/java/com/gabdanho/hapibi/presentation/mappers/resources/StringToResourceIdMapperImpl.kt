package com.gabdanho.hapibi.presentation.mappers.resources

import com.gabdanho.hapibi.R
import com.gabdanho.hapibi.presentation.model.StringResNamePresentation

/**
 * Реализация маппера, которая сопоставляет все возможные
 * идентификаторы из presentation слоя с конкретными ресурсами строк в R.string.
 * Используется для отображения локализованных текстов в UI.
 */
class StringToResourceIdMapperImpl : StringToResourceIdMapper {
    private val resourceMapPresentation = mapOf(
        StringResNamePresentation.ERROR_TIMEOUT to R.string.error_timeout,
        StringResNamePresentation.ERROR_AI_RESPONSE to R.string.error_ai_response,
        StringResNamePresentation.ERROR_GET_FRIENDS to R.string.error_get_friends,
        StringResNamePresentation.ERROR_LOGOUT to R.string.error_logout,
        StringResNamePresentation.ERROR_LOGIN to R.string.error_login
    )

    override fun map(resId: StringResNamePresentation): Int {
        return resourceMapPresentation[resId]
            ?: throw IllegalArgumentException("CANT_MAP_THIS_ID_$resId")
    }
}