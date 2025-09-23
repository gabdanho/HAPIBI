package com.gabdanho.hapibi.presentation.screens.friends

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.gabdanho.hapibi.presentation.component.FriendCard
import com.gabdanho.hapibi.presentation.component.PullToRefreshContainer
import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.presentation.component.VkLogoutButton
import com.gabdanho.hapibi.presentation.model.LoadingState
import com.gabdanho.hapibi.presentation.theme.AppTheme
import com.gabdanho.hapibi.presentation.utils.showUiMessage

/**
 * Экран списка друзей.
 *
 * @param modifier [Modifier] для настройки внешнего вида.
 * @param viewModel ViewModel для экрана друзей.
 */
@Composable
fun FriendsScreen(
    modifier: Modifier = Modifier,
    viewModel: FriendsScreenViewModel = hiltViewModel<FriendsScreenViewModel>(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.uiMessage) {
        uiState.uiMessage?.let {
            context.showUiMessage(
                uiMessage = it,
                clearMessage = { viewModel.handleEvent(event = FriendsScreenEvent.ClearMessage) }
            )
        }
    }

    Scaffold(
        topBar = {
            TopScreenBar(
                onLogoutClick = { viewModel.handleEvent(event = FriendsScreenEvent.Logout) },
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier
    ) { innerPadding ->
        PullToRefreshContainer(
            isRefreshing = uiState.loadingState is LoadingState.Loading,
            onRefresh = { viewModel.handleEvent(event = FriendsScreenEvent.UpdateData) },
            modifier = Modifier.padding(innerPadding)
        ) {
            FriendsList(
                users = uiState.friends,
                onClick = { viewModel.handleEvent(event = FriendsScreenEvent.OnFriendClick(friend = it)) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun FriendsList(
    modifier: Modifier = Modifier,
    users: List<Friend>,
    onClick: (Friend) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(users) { user ->
            FriendCard(
                user = user,
                onClick = onClick,
                modifier = Modifier
                    .padding(AppTheme.dimensions.medium)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun TopScreenBar(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = modifier
    ) {
        VkLogoutButton(
            onClick = onLogoutClick,
            modifier = Modifier.padding(vertical = AppTheme.dimensions.small)
        )
    }
}