package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.gabdanho.hapibi.R
import com.gabdanho.hapibi.presentation.theme.AppTheme

@Composable
fun VkLogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = AppTheme.dimensions.logoutIconSize,
    shape: Shape = AppTheme.shapes.logoutButtonShape,
    backgroundColor: Color = AppTheme.colors.azureA100,
    tintColor: Color = AppTheme.colors.tintColor,
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
            tint = tintColor,
            contentDescription = stringResource(R.string.content_logout),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}