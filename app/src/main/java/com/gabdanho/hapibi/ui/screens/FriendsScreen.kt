package com.gabdanho.hapibi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabdanho.hapibi.ui.custom.FriendCard
import com.gabdanho.hapibi.ui.custom.VkButton
import com.gabdanho.hapibi.ui.model.UserData
import com.gabdanho.hapibi.ui.custom.VkLogoutButton

@Composable
fun FriendsScreen(
    users: List<UserData>,
    onLogoutClick: () -> Unit,
    onCongratButtonClick: (UserData) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopScreenBar(onLogoutClick) },
        modifier = modifier.windowInsetsPadding(WindowInsets.systemBars)
    ) { innerPadding ->
        FriendsList(
            users,
            onCongratButtonClick,
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun FriendsList(
    users: List<UserData>,
    onClick: (UserData) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(users) { user ->
            Column {
                FriendCard(
                    user = user,
                    isShowButton = true,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
fun TopScreenBar(
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = modifier.fillMaxWidth()
    ) {
        VkLogoutButton(onLogoutClick)
    }
}

@Preview
@Composable
private fun FriendsScreenPreview() {
    FriendCard(
        user = UserData(
            imageUrl = "https://sun9-76.userapi.com/impg/LtKVukUG5k3vOQCkk8RTyAGkEmrH34geTz3A6Q/mzT_yti1W84.jpg?size=604x604&quality=95&sign=df0eed2b2a5be62517033a2b51b65e22&type=album",
            firstName = "Bogdan",
            lastName = "Babenko",
            birthDay = "15.06.2004"
        )
    )
}