package com.gabdanho.hapibi.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gabdanho.hapibi.presentation.navigation.NavigationAction
import com.gabdanho.hapibi.presentation.navigation.ObserveAsEvents
import com.gabdanho.hapibi.presentation.navigation.appGraph
import com.gabdanho.hapibi.presentation.navigation.model.AppGraph

/**
 * Главный экран приложения, содержащий навигационный хост.
 *
 * @param viewModel ViewModel экрана [MainScreenViewModel]. По умолчанию создается через Hilt.
 */
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel<MainScreenViewModel>()
) {
    val navigator = viewModel.navigator
    val isAuthorized = viewModel.isUserAuthorized()
    val startDestination = if (isAuthorized) AppGraph.FriendsScreen else AppGraph.LoginScreen
    val navController = rememberNavController()

    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when(action) {
            is NavigationAction.Navigate -> navController.navigate(
                action.navigationDestination
            ) {
                action.navOptions(this)
            }
            NavigationAction.NavigateToPopBackStack -> navController.popBackStack()
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        contentAlignment = Alignment.TopCenter
    ) {
        appGraph()
    }
}