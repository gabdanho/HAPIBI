package com.gabdanho.hapibi.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gabdanho.hapibi.R
import com.gabdanho.hapibi.presentation.utils.showUiMessage
import com.vk.id.auth.VKIDAuthUiParams
import com.vk.id.onetap.common.OneTapStyle
import com.vk.id.onetap.compose.onetap.OneTap

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginScreenViewModel = hiltViewModel<LoginScreenViewModel>()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LaunchedEffect(uiState.uiMessage) {
            uiState.uiMessage?.let {
                context.showUiMessage(
                    uiMessage = it,
                    clearMessage = { viewModel.handleEvent(event = LoginScreenEvent.ClearMessage) }
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.padding(bottom = 32.dp)
        )
        OneTap(
            onAuth = { _, accessToken ->
                viewModel.handleEvent(event = LoginScreenEvent.OnLogin(token = accessToken.token))
            },
            onFail = { _, fail ->
                viewModel.handleEvent(event = LoginScreenEvent.UpdateErrorMessage(details = fail.description))
            },
            signInAnotherAccountButtonEnabled = true,
            style = OneTapStyle.system(context),
            authParams = VKIDAuthUiParams {
                scopes = setOf("vkid.personal_info", "friends", "messages")
            },
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}