package com.gabdanho.hapibi.presentation

import com.gabdanho.hapibi.presentation.model.LoadingState

/**
 * UI-состояние для MainActivity.
 *
 * @property loadingState Текущее состояние загрузки.
 */
data class MainActivityUiState(
    val loadingState: LoadingState? = null,
)