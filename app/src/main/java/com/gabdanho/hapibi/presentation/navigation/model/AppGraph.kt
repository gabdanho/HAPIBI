package com.gabdanho.hapibi.presentation.navigation.model

import com.gabdanho.hapibi.presentation.model.Friend
import kotlinx.serialization.Serializable

@Serializable
sealed class AppGraph : NavigationDestination {

    @Serializable
    data object FriendsScreen : AppGraph()

    @Serializable
    data object LoginScreen : AppGraph()

    @Serializable
    data class CongratulationScreen(val friendData: Friend = Friend()) : AppGraph()
}