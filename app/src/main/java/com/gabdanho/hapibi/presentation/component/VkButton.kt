package com.gabdanho.hapibi.presentation.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.gabdanho.hapibi.presentation.theme.AppTheme


/**
 * Кнопка в стиле VK.
 *
 * @param name Текст кнопки.
 * @param onClick Callback при клике.
 * @param modifier [Modifier] для кастомизации.
 * @param colors Цвета кнопки.
 * @param enabled Включена ли кнопка.
 * @param shape Форма кнопки.
 */
@Composable
fun VkButton(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = AppTheme.colors.azureA100,
        contentColor = AppTheme.colors.white,
    ),
    enabled: Boolean = true,
    shape: Shape = AppTheme.shapes.buttonShape,
) {
    Button(
        onClick = onClick,
        shape = shape,
        colors = colors,
        modifier = modifier,
        enabled = enabled,
    ) {
        Text(text = name)
    }
}