package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gabdanho.hapibi.presentation.theme.AppTheme

/**
 * Плейсхолдер загрузки с индикатором [CircularProgressIndicator].
 *
 * @param modifier [Modifier] для кастомизации контейнера.
 */
@Composable
fun LoadingPlaceholder(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = AppTheme.colors.azureA100)
    }
}