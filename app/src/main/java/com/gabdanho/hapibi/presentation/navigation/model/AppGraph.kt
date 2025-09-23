package com.gabdanho.hapibi.presentation.navigation.model

import com.gabdanho.hapibi.presentation.model.Friend
import kotlinx.serialization.Serializable

/**
 * Состояния навигации приложения.
 */
@Serializable
sealed class AppGraph : NavigationDestination {

    /** Экран списка друзей. */
    @Serializable
    data object FriendsScreen : AppGraph()

    /** Экран авторизации. */
    @Serializable
    data object LoginScreen : AppGraph()

    /**
     * Экран поздравления.
     *
     * @property friendData Данные друга, для которого отображается поздравление.
     */
    @Serializable
    data class CongratulationScreen(val friendData: Friend = Friend()) : AppGraph()
}