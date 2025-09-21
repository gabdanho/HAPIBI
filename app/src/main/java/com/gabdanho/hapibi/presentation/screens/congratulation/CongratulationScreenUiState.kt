package com.gabdanho.hapibi.presentation.screens.congratulation

import com.gabdanho.hapibi.presentation.model.LoadingState
import com.gabdanho.hapibi.presentation.model.UiMessage

data class CongratulationScreenUiState(
    val uiMessage: UiMessage? = null,
    val personName: String = "",
    val finishedCongratulation: String = "",
    val holidayInput: String = "",
    val personStatusInput: String = "",
    val styleInput: String = "",
    val importantWordsInput: String = "",
    val fixProblemsInput: String = "",
    val loadingState: LoadingState? = null,
    val isGenerateButtonEnabled: Boolean = false,
    val isFixButtonEnabled: Boolean = false,
)