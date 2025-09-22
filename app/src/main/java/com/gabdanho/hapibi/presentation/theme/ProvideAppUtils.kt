package com.gabdanho.hapibi.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

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

val LocalAppColor = compositionLocalOf {
    lightColors
}

val LocalAppDimension = compositionLocalOf {
    defaultDimensions
}

val LocalAppShape = compositionLocalOf {
    defaultShapes
}