package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.gabdanho.hapibi.presentation.theme.AppTheme

@Composable
fun VkOutlinedButton(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = AppTheme.shapes.buttonShape,
    enabled: Boolean = true,
    border: BorderStroke = BorderStroke(AppTheme.dimensions.outlinedButtonBorderDp, color = AppTheme.colors.azureA100),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = AppTheme.colors.cardContainerColor,
        contentColor = AppTheme.colors.cardContentColor,
    ),
) {
    Button(
        onClick = onClick,
        shape = shape,
        border = border,
        colors = colors,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = name)
    }
}