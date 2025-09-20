package com.gabdanho.hapibi.presentation.screens.congratulation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabdanho.hapibi.domain.interfaces.usecase.SendPromptUseCase
import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.presentation.mappers.toDomain
import com.gabdanho.hapibi.presentation.model.AiRole
import com.gabdanho.hapibi.presentation.model.CompletionRequest
import com.gabdanho.hapibi.presentation.model.LoadingState
import com.gabdanho.hapibi.presentation.model.Message
import com.gabdanho.hapibi.presentation.model.StringResNamePresentation
import com.gabdanho.hapibi.presentation.model.UiMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CongratulationScreenViewModel @Inject constructor(
    private val sendPromptUseCase: SendPromptUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CongratulationScreenUiState())
    val uiState: StateFlow<CongratulationScreenUiState> = _uiState.asStateFlow()

    fun handleEvent(event: CongratulationScreenEvent) {
        when (event) {
            is CongratulationScreenEvent.GetCongratulation -> getCongratulation()
            is CongratulationScreenEvent.UpdateHolidayInput -> updateHolidayInput(value = event.value)
            is CongratulationScreenEvent.UpdateImportantWordsInput -> updateImportantWordsInput(value = event.value)
            is CongratulationScreenEvent.UpdateStyleInput -> updateStyleInput(value = event.value)
            is CongratulationScreenEvent.UpdatePersonStatusInput -> updatePersonStatusInput(value = event.value)
            is CongratulationScreenEvent.ClearMessage -> clearMessage()
        }
    }

    private fun getCongratulation() {
        viewModelScope.launch {
            val completionRequest = createRequest()
            _uiState.update { state -> state.copy(loadingState = LoadingState.Loading) }
            when (
                val result = sendPromptUseCase(
                    completionRequest = completionRequest.toDomain()
                )
            ) {
                is ApiResult.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            finishedCongratulation = result.data,
                            loadingState = LoadingState.Success
                        )
                    }
                }

                is ApiResult.Error.TimeoutError -> {
                    _uiState.update { state ->
                        state.copy(
                            uiMessage = UiMessage(
                                message = StringResNamePresentation.ERROR_TIMEOUT,
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
                                message = StringResNamePresentation.ERROR_AI_RESPONSE,
                                details = result.message
                            ),
                            loadingState = LoadingState.Error
                        )
                    }
                }
            }
        }
    }

    private fun updateHolidayInput(value: String) {
        _uiState.update { state ->
            state.copy(
                holidayInput = value,
                isButtonEnabled = isAllFieldsFilled()
            )
        }
    }

    private fun updatePersonStatusInput(value: String) {
        _uiState.update { state ->
            state.copy(
                personStatusInput = value,
                isButtonEnabled = isAllFieldsFilled()
            )
        }
    }

    private fun updateStyleInput(value: String) {
        _uiState.update { state ->
            state.copy(
                styleInput = value,
                isButtonEnabled = isAllFieldsFilled()
            )
        }
    }

    private fun updateImportantWordsInput(value: String) {
        _uiState.update { state ->
            state.copy(
                importantWordsInput = value,
                isButtonEnabled = isAllFieldsFilled()
            )
        }
    }

    private fun clearMessage() {
        _uiState.update { state -> state.copy(uiMessage = null) }
    }

    private fun createRequest(): CompletionRequest {
        val state = _uiState.value
        val content = PROMPT_TEMPLATE.format(
            state.holidayInput,
            state.personStatusInput,
            state.styleInput,
            state.importantWordsInput,
            state.personName
        )

        return CompletionRequest(
            listOf(
                Message(
                    role = AiRole.SYSTEM,
                    content = SYSTEM_MESSAGE
                ),
                Message(
                    role = AiRole.USER,
                    content = content
                )
            )
        )
    }

    private fun isAllFieldsFilled(): Boolean {
        val state = _uiState.value

        return state.holidayInput.isNotBlank() && state.styleInput.isNotBlank()
                && state.personStatusInput.isNotBlank() && state.importantWordsInput.isNotBlank()
    }

    companion object {
        private const val SYSTEM_MESSAGE =
            "Ты помогаешь составить поздравление с каким-либо праздником для человека"

        private const val PROMPT_TEMPLATE =
            "Напиши поздравление: на %s, этот человек для меня: %s, " +
                    "стиль поздравления: %s, в поздравлении использовать слова: %s. " +
                    "на имя: %s"
    }
}