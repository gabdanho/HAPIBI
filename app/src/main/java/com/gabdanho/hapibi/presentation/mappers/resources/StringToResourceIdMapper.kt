package com.gabdanho.hapibi.presentation.mappers.resources

import androidx.annotation.StringRes
import com.gabdanho.hapibi.presentation.model.StringResNamePresentation

/**
 * Интерфейс для преобразования идентификаторов строк (StringResNamePresentation)
 * в реальные ресурсы строк Android (@StringRes).
 */
interface StringToResourceIdMapper {

    @StringRes
    fun map(resId: StringResNamePresentation): Int
}