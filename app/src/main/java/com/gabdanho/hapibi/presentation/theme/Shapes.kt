package com.gabdanho.hapibi.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

data class Shapes(
    val buttonShape: RoundedCornerShape,
    val logoutButtonShape: RoundedCornerShape,
)

val defaultShapes = Shapes(
    buttonShape = RoundedCornerShape(10.dp),
    logoutButtonShape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
)