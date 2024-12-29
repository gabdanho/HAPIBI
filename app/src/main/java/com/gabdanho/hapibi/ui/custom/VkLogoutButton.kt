package com.gabdanho.hapibi.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gabdanho.hapibi.R
import com.gabdanho.hapibi.ui.theme.AzureA100

@Composable
fun VkLogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .size(60.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
            .background(AzureA100)
            .clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(R.drawable.logout),
            tint = Color.White,
            contentDescription = "Log Out",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}