package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.gabdanho.hapibi.presentation.theme.AzureA100

@Composable
fun VkOutlinedButton(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(10.dp),
    enabled: Boolean = true,
    border: BorderStroke = BorderStroke(4.dp, color = AzureA100),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.White,
        contentColor = Color.Black,
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