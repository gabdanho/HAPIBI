package com.gabdanho.hapibi.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

/**
 * Предоставляет значения UI-утилит (цвета, размеры, формы) через CompositionLocal.
 *
 * @param dimensions Размеры элементов UI.
 * @param colors Цветовая схема.
 * @param shapes Формы элементов UI.
 * @param content Составной контент приложения.
 */
@Composable
fun ProvideAppUtils(
    dimensions: Dimensions,
    colors: Colors,
    shapes: Shapes,
    content: @Composable () -> Unit,
) {
    val appDimensions = remember { dimensions }
    val appColors = remember { colors }
    val appShapes = remember { shapes }

    CompositionLocalProvider(
        LocalAppColor provides appColors,
        LocalAppDimension provides appDimensions,
        LocalAppShape provides appShapes,
        content = content
    )
}

/** CompositionLocal для текущей цветовой схемы приложения. */
val LocalAppColor = compositionLocalOf {
    lightColors
}

/** CompositionLocal для текущих размеров UI элементов. */
val LocalAppDimension = compositionLocalOf {
    defaultDimensions
}

/** CompositionLocal для текущих форм UI элементов. */
val LocalAppShape = compositionLocalOf {
    defaultShapes
}