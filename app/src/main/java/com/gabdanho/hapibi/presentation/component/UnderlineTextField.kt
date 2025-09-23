package com.gabdanho.hapibi.presentation.component

import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gabdanho.hapibi.presentation.theme.AppTheme

@Composable
fun UnderlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = AppTheme.colors.transparent,
            focusedContainerColor = AppTheme.colors.transparent,
        ),
        modifier = modifier
    )
}