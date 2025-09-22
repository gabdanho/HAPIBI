package com.gabdanho.hapibi.presentation

import com.gabdanho.hapibi.presentation.model.LoadingState

data class MainActivityUiState(
    val loadingState: LoadingState? = null,
    val isReady: Boolean = false,
)