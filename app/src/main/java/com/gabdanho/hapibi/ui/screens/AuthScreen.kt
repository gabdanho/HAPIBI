package com.gabdanho.hapibi.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gabdanho.hapibi.R
import com.gabdanho.hapibi.ui.HapibiViewModel
import com.vk.id.auth.VKIDAuthUiParams
import com.vk.id.onetap.common.OneTapStyle
import com.vk.id.onetap.compose.onetap.OneTap

@Composable
fun AuthScreen(
    context: Context,
    viewModel: HapibiViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.padding(bottom = 32.dp)
        )
        OneTap(
            onAuth = { _, accessToken ->
                viewModel.updateUserToken(accessToken.token)
            },
            onFail = { _, fail ->
                viewModel.updateMessage(fail.description)
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