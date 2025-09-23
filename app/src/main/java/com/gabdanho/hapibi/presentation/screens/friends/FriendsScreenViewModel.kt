package com.gabdanho.hapibi.presentation.screens.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabdanho.hapibi.domain.interfaces.usecase.DeleteAccessTokenUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.GetAccessTokenUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.GetFriendsUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.LogoutUseCase
import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.presentation.mappers.toPresentation
import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.presentation.model.LoadingState
import com.gabdanho.hapibi.presentation.model.StringResNamePresentation
import com.gabdanho.hapibi.presentation.model.UiMessage
import com.gabdanho.hapibi.presentation.navigation.Navigator
import com.gabdanho.hapibi.presentation.navigation.model.AppGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.map

/**
 * ViewModel экрана друзей.
 *
 * @property navigator Навигатор для перемещения между экранами.
 * @property getFriendsUseCase UseCase для получения списка друзей.
 * @property logoutUseCase UseCase для выхода из аккаунта.
 * @property getAccessTokenUseCase UseCase для получения токена доступа.
 * @property deleteAccessTokenUseCase UseCase для удаления токена.
 */
@HiltViewModel
class FriendsScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getFriendsUseCase: GetFriendsUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val deleteAccessTokenUseCase: DeleteAccessTokenUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendsScreenUiState())
    val uiState: StateFlow<FriendsScreenUiState> = _uiState.asStateFlow()

    init {
        updateData()
    }

    fun handleEvent(event: FriendsScreenEvent) {
        when (event) {
            is FriendsScreenEvent.Logout -> logout()
            is FriendsScreenEvent.OnFriendClick -> onFriendClick(friend = event.friend)
            is FriendsScreenEvent.UpdateData -> updateData()
            FriendsScreenEvent.ClearMessage -> clearMessage()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            when (val result = logoutUseCase()) {
                is ApiResult.Success -> {
                    deleteAccessTokenUseCase()
                    navigator.navigate(
                        destination = AppGraph.LoginScreen,
                        navOptions = { popUpTo(0) { inclusive } }
                    )
                }

                is ApiResult.Error.TimeoutError -> {
                    _uiState.update { state ->
                        state.copy(
                            uiMessage = UiMessage(
                                textResName = StringResNamePresentation.ERROR_TIMEOUT,
                                details = result.message
                            ),
                            loadingState = LoadingState.Error
                        )
                    }
                }

                is ApiResult.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            uiMessage = UiMessage(
                                textResName = StringResNamePresentation.ERROR_LOGOUT,
                                details = result.message
                            ),
                            loadingState = LoadingState.Error
                        )
                    }
                }
            }
        }
    }

    private fun onFriendClick(friend: Friend) {
        viewModelScope.launch {
            navigator.navigate(
                destination = AppGraph.CongratulationScreen(friendData = friend)
            )
        }
    }

    private fun updateData() {
        viewModelScope.launch {
            val token = getAccessTokenUseCase()

            _uiState.update { state -> state.copy(loadingState = LoadingState.Loading) }
            token?.let {
                when (val result = getFriendsUseCase(token = token)) {
                    is ApiResult.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                friends = result.data.map { it.toPresentation() },
                                loadingState = LoadingState.Success
                            )
                        }
                    }

                    is ApiResult.Error.TimeoutError -> {
                        _uiState.update { state ->
                            state.copy(
                                uiMessage = UiMessage(
                                    textResName = StringResNamePresentation.ERROR_TIMEOUT,
                                    details = result.message
                                ),
                                loadingState = LoadingState.Error
                            )
                        }
                    }

                    is ApiResult.Error -> {
                        _uiState.update { state ->
                            state.copy(
                                uiMessage = UiMessage(
                                    textResName = StringResNamePresentation.ERROR_GET_FRIENDS,
                                    details = result.message
                                ),
                                loadingState = LoadingState.Error
                            )
                        }
                    }
                }
            }
        }
    }

    private fun clearMessage() {
        _uiState.update { state -> state.copy(uiMessage = null) }
    }
}