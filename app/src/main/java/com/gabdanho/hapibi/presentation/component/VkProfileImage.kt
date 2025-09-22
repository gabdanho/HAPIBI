package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.gabdanho.hapibi.presentation.theme.AppTheme

@Composable
fun VkProfileImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    borderWidth: Dp = AppTheme.dimensions.profileImageBorderDp,
    size: Dp = AppTheme.dimensions.profileImageSize,
    borderColor: Color = AppTheme.colors.azureA100,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = imageUrl,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .border(width = borderWidth, shape = shape, color = borderColor)
            .clip(shape)
            .size(size)
    )
}