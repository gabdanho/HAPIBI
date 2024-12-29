package com.gabdanho.hapibi.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabdanho.hapibi.data.local.repository.UserTokenDataRepository
import com.gabdanho.hapibi.data.remote.repository.VkApiService
import com.gabdanho.hapibi.ui.mappers.toUserData
import com.gabdanho.hapibi.ui.model.UserData
import com.vk.id.VKID
import com.vk.id.logout.VKIDLogoutCallback
import com.vk.id.logout.VKIDLogoutFail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HapibiViewModel @Inject constructor(
    private val userTokenRepo: UserTokenDataRepository,
    private val vkApiService: VkApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(HapibiUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val userKey = userTokenRepo.getUserKey()
        if (userKey.isNotEmpty()) {
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
}

data class HapibiUiState(
    val userKey: String = "",
    val message: String = "",
    val isAuthorized: Boolean = false,
    val users: List<UserData> = emptyList()
)