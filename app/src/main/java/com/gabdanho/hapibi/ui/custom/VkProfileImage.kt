package com.gabdanho.hapibi.ui.custom

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gabdanho.hapibi.ui.theme.AzureA100

@Composable
fun VkProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = imageUrl,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(vertical = 16.dp)
            .border(width = 5.dp, shape = CircleShape, color = AzureA100)
            .clip(CircleShape)
            .size(200.dp)
    )
}