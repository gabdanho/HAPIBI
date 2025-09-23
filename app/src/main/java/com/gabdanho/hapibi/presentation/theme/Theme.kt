package com.gabdanho.hapibi.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Темизация приложения Hapibi.
 *
 * @param useDarkTheme Использовать темную тему (по умолчанию берется системная настройка).
 * @param content Составной контент приложения.
 */
@Composable
fun HapibiTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (useDarkTheme) darkColors else lightColors
    val dimensions = defaultDimensions
    val shapes = defaultShapes

    ProvideAppUtils(
        dimensions = dimensions,
        colors = colors,
        shapes = shapes
    ) {
        MaterialTheme(
            content = content,
            colorScheme = lightColorScheme(
                background = colors.background,
                onBackground = colors.onBackground,
                primary = colors.primary,
                onSurface = colors.onSurface
            )
        )
    }
}

/**
 * Объект для доступа к текущей теме приложения (цвета, размеры, формы).
 */
object AppTheme {

    /** Текущая цветовая схема. */
    val colors: Colors
        @Composable
        get() = LocalAppColor.current

    /** Текущие размеры UI элементов. */
    val dimensions: Dimensions
        @Composable
        get() = LocalAppDimension.current

    /** Текущие формы UI элементов. */
    val shapes: Shapes
        @Composable
        get() = LocalAppShape.current
}