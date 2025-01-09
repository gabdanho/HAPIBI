package com.gabdanho.hapibi.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gabdanho.hapibi.ui.screens.AuthScreen
import com.gabdanho.hapibi.ui.HapibiViewModel
import com.gabdanho.hapibi.ui.model.UserData
import com.gabdanho.hapibi.ui.screens.FriendsScreen
import com.gabdanho.hapibi.ui.screens.SelectedFriendScreen

@Composable
fun HapibiNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: Context,
    viewModel: HapibiViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val message = uiState.value.message
    val congratMessage = uiState.value.congratMessage
    val isGeneratingMessage = uiState.value.isGeneratingMessage
    val isAuthorized = uiState.value.isAuthorized
    var selectedUserData by remember { mutableStateOf<UserData?>(null) }

    LaunchedEffect(uiState.value.isAuthorized) {
        navController.navigate(if (!isAuthorized) NavigationRoutes.AUTH.name else NavigationRoutes.FRIENDS.name) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }

        if (isAuthorized)
            viewModel.getUserFriends()
    }

    NavHost(
        navController = navController,
        startDestination = if (isAuthorized) NavigationRoutes.FRIENDS.name else NavigationRoutes.AUTH.name
    ) {
        composable(NavigationRoutes.AUTH.name) {
            AuthScreen(viewModel = viewModel, context = context, modifier = modifier)
        }

        composable(NavigationRoutes.FRIENDS.name) {
            FriendsScreen(
                users = uiState.value.users,
                onLogoutClick = viewModel::logOutFromAccount,
                onCongratButtonClick = { data ->
                    selectedUserData = data
                    navController.navigate(NavigationRoutes.SELECTED_FRIEND.name)
                }
            )
        }

        composable(NavigationRoutes.SELECTED_FRIEND.name) {
            SelectedFriendScreen(
                userData = selectedUserData!!,
                generateCongrat = viewModel::generateCongrat,
                isGeneratingMessage = isGeneratingMessage,
                congratMessage = congratMessage,
                copyCongrat = viewModel::copyCongrat,
                changeCongratText = viewModel::updateCongratMessage,
                backScreen = { navController.popBackStack() },
                clearCongratText = viewModel::clearCongratMessage
            )
        }
    }

    LaunchedEffect(message) {
        if (message != "") {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.updateMessage("")
        }
    }
}