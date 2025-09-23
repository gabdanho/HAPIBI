package com.gabdanho.hapibi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabdanho.hapibi.domain.interfaces.usecase.DeleteAccessTokenUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.RefreshTokenUseCase
import com.gabdanho.hapibi.domain.interfaces.usecase.SetAccessTokenUseCase
import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.presentation.model.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel для MainActivity.
 *
 * Управляет состоянием загрузки, обновлением токена и сохранением/удалением токена.
 *
 * @property setAccessTokenUseCase UseCase для сохранения токена.
 * @property refreshTokenUseCase UseCase для обновления токена.
 * @property deleteAccessTokenUseCase UseCase для удаления токена.
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val setAccessTokenUseCase: SetAccessTokenUseCase,
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val deleteAccessTokenUseCase: DeleteAccessTokenUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUiState())
    val uiState: StateFlow<MainActivityUiState> = _uiState.asStateFlow()

    fun handleEvent(event: MainActivityEvent) {
        when (event) {
            is MainActivityEvent.RefreshToken -> refreshToken()
        }
    }

    fun refreshToken() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loadingState = LoadingState.Loading) }
            when (val result = refreshTokenUseCase()) {
                is ApiResult.Success -> {
                    if (result.data != null) {
                        setAccessTokenUseCase(token = result.data)
                    }
                    else {
                        deleteAccessTokenUseCase()
                    }
                    _uiState.update { state -> state.copy(loadingState = LoadingState.Success) }
                }

                is ApiResult.Error -> {
                    _uiState.update { state -> state.copy(loadingState = LoadingState.Error) }
                }
            }
        }
    }
}