package com.gabdanho.hapibi.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabdanho.hapibi.domain.interfaces.usecase.SetAccessTokenUseCase
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

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val setAccessTokenUseCase: SetAccessTokenUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()

    fun handleEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.OnLogin -> onLogin(token = event.token)
            is LoginScreenEvent.UpdateErrorMessage -> updateErrorMessage(details = event.details)
            is LoginScreenEvent.ClearMessage -> clearMessage()
        }
    }

    private fun onLogin(token: String) {
        viewModelScope.launch {
            setAccessTokenUseCase(token = token)
            navigator.navigate(
                destination = AppGraph.FriendsScreen,
                navOptions = { popUpTo(0) { inclusive } }
            )
        }
    }

    private fun updateErrorMessage(details: String) {
        _uiState.update { state ->
            state.copy(
                uiMessage = UiMessage(
                    message = StringResNamePresentation.ERROR_LOGIN,
                    details = details
                )
            )
        }
    }

    private fun clearMessage() {
        _uiState.update { state -> state.copy(uiMessage = null) }
    }
}