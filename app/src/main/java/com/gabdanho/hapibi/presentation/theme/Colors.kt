package com.gabdanho.hapibi.presentation.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val azureA100: Color = Color(0xFF0077FF),
    val medRosa: Color = Color(0xFFEDEEF0),
    val background: Color = Color(0xFFF8F8F8),
    val black: Color = Color(0xFF000000),
    val white: Color = Color(0xFFFFFFFF),
    val tintColor: Color = Color(0xFFFFFFFF),
    val transparent: Color = Color(0x00000000),
)

val lightColors = Colors()