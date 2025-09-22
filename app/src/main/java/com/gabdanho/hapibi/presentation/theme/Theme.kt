package com.gabdanho.hapibi.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun HapibiTheme(
    content: @Composable () -> Unit,
) {
    val colors = lightColors
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
                background = colors.background
            )
        )
    }
}

object AppTheme {

    val colors: Colors
        @Composable
        get() = LocalAppColor.current

    val dimensions: Dimensions
        @Composable
        get() = LocalAppDimension.current

    val shapes: Shapes
        @Composable
        get() = LocalAppShape.current
}