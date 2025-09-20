package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gabdanho.hapibi.R
import com.gabdanho.hapibi.presentation.theme.AzureA100

@Composable
fun VkLogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 60.dp,
    shape: Shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
    backgroundColor: Color = AzureA100,
    textColor: Color = Color.White,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(backgroundColor)
            .clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(R.drawable.logout),
            tint = textColor,
            contentDescription = "Logout",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}