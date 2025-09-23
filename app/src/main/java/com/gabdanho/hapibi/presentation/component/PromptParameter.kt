package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gabdanho.hapibi.presentation.theme.AppTheme

/**
 * Поле ввода параметра промпта с подписью и текстовым полем.
 *
 * @param name Заголовок параметра.
 * @param value Текущее значение.
 * @param onValueChange Callback при изменении текста.
 * @param modifier [Modifier] для кастомизации контейнера.
 */
@Composable
fun PromptParameter(
    name: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            modifier = Modifier.padding(end = AppTheme.dimensions.small)
        )
        UnderlineTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .padding(bottom = AppTheme.dimensions.small)
                .fillMaxWidth()
        )
    }
}
