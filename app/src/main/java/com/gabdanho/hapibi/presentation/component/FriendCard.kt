package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gabdanho.hapibi.presentation.model.Friend

@Composable
fun FriendCard(
    user: Friend,
    modifier: Modifier = Modifier,
    onClick: (Friend) -> Unit = { },
    cardColors: CardColors = CardDefaults.cardColors(
        containerColor = Color.White,
        contentColor = Color.Black
    ),
) {
    Card(
        modifier = modifier,
        colors = cardColors
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            VkProfileImage(
                imageUrl = user.imageUrl,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Text(
                text = "${user.firstName} ${user.lastName}",
                style = MaterialTheme.typography.headlineSmall
            )
            if (user.birthDayDate.isNotBlank()) {
                Text(
                    text = user.birthDayDate,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            VkButton(
                onClick = { onClick(user) },
                name = "Поздравить",
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}