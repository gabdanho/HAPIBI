package com.gabdanho.hapibi.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gabdanho.hapibi.ui.screens.AuthScreen
import com.gabdanho.hapibi.ui.HapibiViewModel
import com.gabdanho.hapibi.ui.screens.FriendsScreen

@Composable
fun HapibiNavGraph(
    navController: NavHostController,
    context: Context,
    viewModel: HapibiViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()
    val message = uiState.value.message
    val isAuthorized = uiState.value.isAuthorized

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
                onLogoutClick = viewModel::logOutFromAccount
            )
        }
    }

    LaunchedEffect(message) {
        if (message != "") {
            Toast.makeText(context, "Ошибка входа: ${message}", Toast.LENGTH_SHORT).show()
            viewModel.updateMessage("")
        }
    }
}