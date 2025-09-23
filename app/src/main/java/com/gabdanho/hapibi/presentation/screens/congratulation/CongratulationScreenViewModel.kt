package com.gabdanho.hapibi.presentation.screens.congratulation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabdanho.hapibi.domain.interfaces.usecase.SendPromptUseCase
import com.gabdanho.hapibi.domain.model.ApiResult
import com.gabdanho.hapibi.presentation.mappers.toDomain
import com.gabdanho.hapibi.presentation.model.AiRole
import com.gabdanho.hapibi.presentation.model.CompletionRequest
import com.gabdanho.hapibi.presentation.model.CongratulationMessagePromptType
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

/**
 * ViewModel экрана поздравления.
 *
 * @property sendPromptUseCase UseCase для отправки запроса на генерацию поздравления.
 */
@HiltViewModel
class CongratulationScreenViewModel @Inject constructor(
    private val sendPromptUseCase: SendPromptUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CongratulationScreenUiState())
    val uiState: StateFlow<CongratulationScreenUiState> = _uiState.asStateFlow()

    fun handleEvent(event: CongratulationScreenEvent) {
        when (event) {
            is CongratulationScreenEvent.GenerateCongratulation -> generateCongratulation(promptType = CongratulationMessagePromptType.CREATE_CONGRATULATION)
            is CongratulationScreenEvent.UpdateHolidayInput -> updateHolidayInput(value = event.value)
            is CongratulationScreenEvent.UpdateImportantWordsInput -> updateImportantWordsInput(
                value = event.value
            )

            is CongratulationScreenEvent.UpdateStyleInput -> updateStyleInput(value = event.value)
            is CongratulationScreenEvent.UpdatePersonStatusInput -> updatePersonStatusInput(value = event.value)
            is CongratulationScreenEvent.InitPersonName -> initPersonName(name = event.name)
            is CongratulationScreenEvent.ClearMessage -> clearMessage()
            is CongratulationScreenEvent.FixCongratulation -> generateCongratulation(promptType = CongratulationMessagePromptType.FIX_CONGRATULATION)
            is CongratulationScreenEvent.UpdateFixProblemsInput -> updateFixProblemsInput(value = event.value)
        }
    }

    private fun generateCongratulation(promptType: CongratulationMessagePromptType) {
        viewModelScope.launch {
            val completionRequest = createRequest(promptType = promptType)
            _uiState.update { state ->
                state.copy(
                    loadingState = LoadingState.Loading,
                    isGenerateButtonEnabled = false
                )
            }
            when (
                val result = sendPromptUseCase(
                    completionRequest = completionRequest.toDomain()
                )
            ) {
                is ApiResult.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            finishedCongratulation = result.data,
                            loadingState = LoadingState.Success,
                            styleInput = "",
                            personStatusInput = "",
                            importantWordsInput = "",
                            holidayInput = "",
                            fixProblemsInput = "",
                            isGenerateButtonEnabled = false,
                            isFixButtonEnabled = false
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
                            loadingState = LoadingState.Error,
                            isGenerateButtonEnabled = true
                        )
                    }
                }

                is ApiResult.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            uiMessage = UiMessage(
                                textResName = StringResNamePresentation.ERROR_AI_RESPONSE,
                                details = result.message
                            ),
                            loadingState = LoadingState.Error,
                            isGenerateButtonEnabled = true
                        )
                    }
                }
            }
        }
    }

    private fun updateHolidayInput(value: String) {
        _uiState.update { state ->
            state.copy(
                holidayInput = value
            )
        }
        isAllFieldsFilled()
    }

    private fun updatePersonStatusInput(value: String) {
        _uiState.update { state ->
            state.copy(
                personStatusInput = value
            )
        }
        isAllFieldsFilled()
    }

    private fun updateStyleInput(value: String) {
        _uiState.update { state ->
            state.copy(
                styleInput = value
            )
        }
        isAllFieldsFilled()
    }

    private fun updateImportantWordsInput(value: String) {
        _uiState.update { state ->
            state.copy(
                importantWordsInput = value
            )
        }
        isAllFieldsFilled()
    }

    private fun updateFixProblemsInput(value: String) {
        _uiState.update { state ->
            state.copy(
                fixProblemsInput = value,
                isFixButtonEnabled = value.isNotBlank()
            )
        }
    }

    private fun initPersonName(name: String) {
        _uiState.update { state ->
            state.copy(
                personName = name
            )
        }
    }

    private fun clearMessage() {
        _uiState.update { state -> state.copy(uiMessage = null) }
    }

    private fun createRequest(promptType: CongratulationMessagePromptType): CompletionRequest {
        val state = _uiState.value

        var content: String
        var messageForSystem: String

        when (promptType) {
            CongratulationMessagePromptType.CREATE_CONGRATULATION -> {
                messageForSystem = SYSTEM_MESSAGE_TO_CONGRATULATE
                content = PROMPT_TEMPLATE.format(
                    state.holidayInput,
                    state.personStatusInput,
                    state.styleInput,
                    state.importantWordsInput,
                    state.personName
                )
            }

            CongratulationMessagePromptType.FIX_CONGRATULATION -> {
                messageForSystem =
                    SYSTEM_MESSAGE_TO_FIX_CONGRATULATION.format(state.finishedCongratulation)
                content = state.fixProblemsInput
            }
        }

        return CompletionRequest(
            listOf(
                Message(
                    role = AiRole.SYSTEM,
                    content = messageForSystem
                ),
                Message(
                    role = AiRole.USER,
                    content = content
                )
            )
        )
    }

    private fun isAllFieldsFilled() {
        val state = _uiState.value

        val isFilled = state.holidayInput.isNotBlank() && state.styleInput.isNotBlank()
                && state.personStatusInput.isNotBlank() && state.importantWordsInput.isNotBlank()

        _uiState.update { state -> state.copy(isGenerateButtonEnabled = isFilled) }
    }

    companion object {
        private const val SYSTEM_MESSAGE_TO_CONGRATULATE =
            "Ты помогаешь составить поздравление с каким-либо праздником для человека. ОТПРАВЛЯЕШЬ ТОЛЬКО ИТОГОВОЕ ПОЗДРАВЛЕНИЕ!!! НЕ ИСПОЛЬЗУЙ В КОНЦЕ [Твое имя] и т.п."

        private const val SYSTEM_MESSAGE_TO_FIX_CONGRATULATION =
            "Ты должен исправить недочёты в поздравлении, которые укажет пользователь. Вот поздравление: %s. ОТПРАВЛЯЕШЬ ТОЛЬКО ИЗМЕНЁННОЕ ИТОГОВОЕ ПОЗДРАВЛЕНИЕ!!!"

        private const val PROMPT_TEMPLATE =
            "Напиши поздравление: на %s, этот человек для меня: %s, " +
                    "стиль поздравления: %s, в поздравлении использовать слова: %s. " +
                    "на имя: %s"
    }
}