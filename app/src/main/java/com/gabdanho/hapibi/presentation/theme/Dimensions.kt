package com.gabdanho.hapibi.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val medium: Dp,
    val small: Dp,
    val large: Dp,
    val logoutIconSize: Dp,
    val profileImageSize: Dp,
    val profileImageBorderDp: Dp,
    val outlinedButtonBorderDp: Dp,
)

val defaultDimensions = Dimensions(
    small = 8.dp,
    medium = 16.dp,
    large = 32.dp,
    logoutIconSize = 60.dp,
    profileImageSize = 200.dp,
    profileImageBorderDp = 5.dp,
    outlinedButtonBorderDp = 4.dp,
)