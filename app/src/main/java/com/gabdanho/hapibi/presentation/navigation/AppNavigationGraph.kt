package com.gabdanho.hapibi.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.presentation.navigation.model.AppGraph
import com.gabdanho.hapibi.presentation.navigation.model.nav_type.FriendNavType
import com.gabdanho.hapibi.presentation.screens.congratulation.CongratulationScreen
import com.gabdanho.hapibi.presentation.screens.friends.FriendsScreen
import com.gabdanho.hapibi.presentation.screens.login.LoginScreen
import kotlin.reflect.typeOf

fun NavGraphBuilder.appGraph(
    modifier: Modifier = Modifier
) {
    composable<AppGraph.FriendsScreen> {
        FriendsScreen()
    }

    composable<AppGraph.LoginScreen> {
        LoginScreen()
    }

    composable<AppGraph.CongratulationScreen>(
        typeMap = mapOf(typeOf<Friend>() to FriendNavType())
    ) {
        val args = it.toRoute<AppGraph.CongratulationScreen>()
        CongratulationScreen(friendData = args.friendData)
    }
}