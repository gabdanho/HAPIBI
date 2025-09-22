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
import androidx.compose.ui.res.stringResource
import com.gabdanho.hapibi.R
import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.presentation.theme.AppTheme

@Composable
fun FriendCard(
    user: Friend,
    modifier: Modifier = Modifier,
    onClick: ((Friend) -> Unit)? = null,
    cardColors: CardColors = CardDefaults.cardColors(
        containerColor = AppTheme.colors.cardContainerColor,
        contentColor = AppTheme.colors.cardContentColor
    ),
) {
    Card(
        modifier = modifier,
        colors = cardColors
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppTheme.dimensions.medium)
        ) {
            VkProfileImage(
                imageUrl = user.imageUrl
            )
            Text(
                text = stringResource(
                    R.string.text_first_last_name_person,
                    user.firstName,
                    user.lastName
                ),
                style = MaterialTheme.typography.headlineSmall
            )
            if (user.birthDayDate.isNotBlank()) {
                Text(
                    text = user.birthDayDate,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            if (onClick != null) {
                VkButton(
                    onClick = { onClick(user) },
                    name = stringResource(R.string.button_congratulate)
                )
            }
        }
    }
}