package com.gabdanho.hapibi.ui.custom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gabdanho.hapibi.ui.theme.AzureA100

@Composable
fun VkOutlinedButton(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(4.dp, color = AzureA100),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
        ),
        modifier = modifier
    ) {
        Text(text = name)
    }
}