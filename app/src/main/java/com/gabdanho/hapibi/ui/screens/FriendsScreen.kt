package com.gabdanho.hapibi.ui.screens

import android.util.Log
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabdanho.hapibi.ui.model.UserData
import com.gabdanho.hapibi.ui.custom.VkButton
import com.gabdanho.hapibi.ui.custom.VkLogoutButton
import com.gabdanho.hapibi.ui.custom.VkProfileImage

@Composable
fun FriendsScreen(
    users: List<UserData>,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopScreenBar(onLogoutClick) },
        modifier = modifier.windowInsetsPadding(WindowInsets.systemBars)
    ) { innerPadding ->
        FriendsList(
            users,
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun FriendsList(
    users: List<UserData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(users) { user ->
            FriendCard(user)
        }
    }
}

@Composable
fun FriendCard(
    user: UserData,
    modifier: Modifier = Modifier
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
            VkProfileImage(user.imageUrl)
            Text(
                text = "${user.firstName} ${user.lastName}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = user.birthDay,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            VkButton({ /* TODO() */ }, modifier = Modifier.padding(bottom = 16.dp))
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