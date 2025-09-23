package com.gabdanho.hapibi.presentation.theme

import androidx.compose.ui.graphics.Color

/**
 * Цветовая схема приложения.
 */
data class Colors(
    val azureA100: Color,
    val medRosa: Color,
    val black: Color,
    val white: Color,
    val cardContainerColor: Color,
    val cardContentColor: Color,
    val tintColor: Color,
    val transparent: Color,
    val onBackground: Color,
    val background: Color,
    val primary: Color,
    val onSurface: Color,
)

val lightColors = Colors(
    azureA100 = Color(0xFF0077FF),
    medRosa = Color(0xFFEDEEF0),
    black = Color(0xFF000000),
    white = Color(0xFFFFFFFF),
    cardContainerColor = Color(0xFFFFFFFF),
    cardContentColor = Color(0xFF000000),
    tintColor = Color(0xFFFFFFFF),
    transparent = Color(0x00000000),
    onBackground = Color(0xFF000000),
    background = Color(0xFFF8F8F8),
    primary = Color(0xFF000000),
    onSurface = Color(0xFF000000)
)

val darkColors = Colors(
    azureA100 = Color(0xFF0077FF),
    medRosa = Color(0xFFEDEEF0),
    black = Color(0xFF000000),
    white = Color(0xFFFFFFFF),
    cardContainerColor = Color(0xFF151515),
    cardContentColor = Color(0xFFFFFFFF),
    tintColor = Color(0xFFFFFFFF),
    transparent = Color(0x00000000),
    onBackground = Color(0xFFFFFFFF),
    background = Color(0xFF000000),
    primary = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF)
)