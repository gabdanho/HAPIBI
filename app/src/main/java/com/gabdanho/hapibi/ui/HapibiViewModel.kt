package com.gabdanho.hapibi.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabdanho.hapibi.data.local.repository.UserTokenDataRepository
import com.gabdanho.hapibi.data.remote.repository.CompletionRequest
import com.gabdanho.hapibi.data.remote.repository.Message
import com.gabdanho.hapibi.data.remote.repository.AimlapiService
import com.gabdanho.hapibi.data.remote.repository.VkApiService
import com.gabdanho.hapibi.ui.mappers.toUserData
import com.gabdanho.hapibi.ui.model.CongratPrompt
import com.gabdanho.hapibi.ui.model.UserData
import com.vk.id.VKID
import com.vk.id.logout.VKIDLogoutCallback
import com.vk.id.logout.VKIDLogoutFail
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HapibiViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userTokenRepo: UserTokenDataRepository,
    private val vkApiService: VkApiService,
    private val aimlapiService: AimlapiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HapibiUiState())
    val uiState = _uiState.asStateFlow()

    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    init {
        if (VKID.instance.accessToken != null) {
            val userKey = userTokenRepo.getUserKey()
            getUserFriends()
            _uiState.update { currentState ->
                currentState.copy(
                    isAuthorized = true,
                    userKey = userKey
                )
            }
        }
    }

    fun updateUserToken(token: String) {
        userTokenRepo.putUserKey(token)
        _uiState.update { currentState ->
            currentState.copy(
                isAuthorized = token != "",
                userKey = token
            )
        }

    }

    fun updateMessage(text: String) {
        _uiState.update { currentState ->
            currentState.copy(message = text)
        }
    }

    fun logOutFromAccount() {
        viewModelScope.launch {
            VKID.instance.logout(
                callback = object : VKIDLogoutCallback {
                    override fun onSuccess() {
                        updateUserToken("")
                    }
                    override fun onFail(fail: VKIDLogoutFail) {
                        when (fail) {
                            is VKIDLogoutFail.FailedApiCall -> fail.description
                            is VKIDLogoutFail.NotAuthenticated -> fail.description
                            is VKIDLogoutFail.AccessTokenTokenExpired -> fail.description
                        }
                    }
                }
            )
        }
    }

    fun getUserFriends() {
        viewModelScope.launch {
            try {
                val vkResponse = vkApiService.getUserInfo(accessToken = uiState.value.userKey)
                val friends = vkResponse.response.items.map { it.toUserData() }
                _uiState.update { currentState ->
                    currentState.copy(users = friends)
                }
            } catch (e: Exception) {
                Log.e("API ERROR", e.stackTrace.toString())
                Log.e("API ERROR", e.message ?: "Unknown error")
            }
        }
    }

    fun generateCongrat(congratPrompt: CongratPrompt) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isGeneratingMessage = true, congratMessage = "")
            }

            val request = CompletionRequest(
                model = "gpt-4o",
                messages = listOf(
                    Message(
                        role = "system",
                        content = "Ты помогаешь составить поздравление с каким-либо праздником для человека"
                    ),
                    Message(
                        role = "user",
                        content = "Напиши поздравление: на ${congratPrompt.holiday}, " +
                                "этот человек для меня: ${congratPrompt.personStatus}, " +
                                "стиль поздравления: ${congratPrompt.styleCongrat}, " +
                                "в поздравлении использовать слова: ${congratPrompt.importantWords}." +
                                "на имя: ${congratPrompt.firstName}"
                    )
                )
            )

            try {
                val aiResponse = aimlapiService.generateCongrat(request)
                val message = aiResponse.choices.first().message.content
                _uiState.update { currentState ->
                    currentState.copy(congratMessage = message)
                }
                Log.d("MESSAGE", uiState.value.congratMessage)
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(message = "Response API")
                }
                Log.e("API ERROR", e.stackTrace.toString())
                Log.e("API ERROR", e.message ?: "Unknown error")
            }

            _uiState.update { currentState ->
                currentState.copy(isGeneratingMessage = false)
            }
        }
    }

    fun updateCongratMessage(prompt: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isGeneratingMessage = true)
            }

            val request = CompletionRequest(
                model = "gpt-4o",
                messages = listOf(
                    Message(
                        role = "system",
                        content = "Ты помогаешь составить поздравление с каким-либо праздником для человека"
                    ),
                    Message(
                        role = "user",
                        content = "${uiState.value.congratMessage}. Исправь здесь следующее: $prompt"
                    )
                )
            )

            try {
                val aiResponse = aimlapiService.generateCongrat(request)
                val message = aiResponse.choices.first().message.content
                _uiState.update { currentState ->
                    currentState.copy(congratMessage = message)
                }
                Log.d("MESSAGE", uiState.value.congratMessage)
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(message = "Response API error")
                }
                Log.e("API ERROR", e.stackTrace.toString())
                Log.e("API ERROR", e.message ?: "Unknown error")
            }

            _uiState.update { currentState ->
                currentState.copy(isGeneratingMessage = false)
            }
        }
    }

    fun copyCongrat(message: String) {
        val clip = ClipData.newPlainText("text", message)
        clipboardManager.setPrimaryClip(clip).also {
            updateMessage("Поздравление скопировано")
            clearCongratMessage()
        }
    }

    fun clearCongratMessage() {
        _uiState.update { currentState ->
            currentState.copy(congratMessage = "")
        }
    }
}

data class HapibiUiState(
    val userKey: String = "",
    val message: String = "",
    val congratMessage: String = "",
    val isGeneratingMessage: Boolean = false,
    val isAuthorized: Boolean = false,
    val users: List<UserData> = emptyList()
)