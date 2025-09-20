package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gabdanho.hapibi.presentation.model.UserData

@Composable
fun FriendCard(
    modifier: Modifier = Modifier,
    user: UserData,
    isShowButton: Boolean = false,
    onClick: (UserData) -> Unit = { }
) {
    Card(
        modifier = modifier
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            VkProfileImage(imageUrl = user.imageUrl)
            Text(
                text = "${user.firstName} ${user.lastName}",
                style = MaterialTheme.typography.headlineSmall
            )
            if (user.birthDay != "") {
                Text(
                    text = user.birthDay,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            if (isShowButton) {
                VkButton(
                    onClick = { onClick(user) },
                    name = "Поздравить",
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}