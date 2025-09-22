package com.gabdanho.hapibi.presentation.screens.congratulation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import com.gabdanho.hapibi.R
import com.gabdanho.hapibi.presentation.component.FriendCard
import com.gabdanho.hapibi.presentation.component.PullToRefreshContainer
import com.gabdanho.hapibi.presentation.component.VkButton
import com.gabdanho.hapibi.presentation.component.VkOutlinedButton
import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.presentation.model.LoadingState
import com.gabdanho.hapibi.presentation.theme.AppTheme
import com.gabdanho.hapibi.presentation.utils.showUiMessage

@Composable
fun CongratulationScreen(
    friendData: Friend,
    modifier: Modifier = Modifier,
    viewModel: CongratulationScreenViewModel = hiltViewModel<CongratulationScreenViewModel>(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(friendData) {
        viewModel.handleEvent(event = CongratulationScreenEvent.InitPersonName(name = friendData.firstName))
    }

    LaunchedEffect(uiState.uiMessage) {
        uiState.uiMessage?.let {
            context.showUiMessage(
                uiMessage = it,
                clearMessage = { viewModel.handleEvent(event = CongratulationScreenEvent.ClearMessage) }
            )
        }
    }

    Scaffold { innerPadding ->
        PullToRefreshContainer(
            isRefreshing = uiState.loadingState is LoadingState.Loading,
            enabled = uiState.loadingState is LoadingState.Error,
            onRefresh = {
                if (uiState.loadingState == LoadingState.Error) {
                    viewModel.handleEvent(
                        event = if (uiState.fixProblemsInput.isNotBlank())
                            CongratulationScreenEvent.FixCongratulation
                        else CongratulationScreenEvent.GenerateCongratulation
                    )
                }
            },
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.verticalScroll(scrollState)
            ) {
                FriendCard(
                    user = friendData,
                    modifier = Modifier
                        .padding(AppTheme.dimensions.medium)
                        .fillMaxWidth()
                )

                PromptParameter(
                    name = stringResource(R.string.text_whats_holiday),
                    value = uiState.holidayInput,
                    onValueChange = {
                        viewModel.handleEvent(
                            event = CongratulationScreenEvent.UpdateHolidayInput(
                                value = it
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppTheme.dimensions.medium)
                )
                PromptParameter(
                    name = stringResource(R.string.text_person_status),
                    value = uiState.personStatusInput,
                    onValueChange = {
                        viewModel.handleEvent(
                            event = CongratulationScreenEvent.UpdatePersonStatusInput(
                                value = it
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppTheme.dimensions.medium)
                )
                PromptParameter(
                    name = stringResource(R.string.text_style_congratulation),
                    value = uiState.styleInput,
                    onValueChange = {
                        viewModel.handleEvent(
                            event = CongratulationScreenEvent.UpdateStyleInput(
                                value = it
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppTheme.dimensions.medium)
                )
                PromptParameter(
                    name = stringResource(R.string.text_important_words),
                    value = uiState.importantWordsInput,
                    onValueChange = {
                        viewModel.handleEvent(
                            event = CongratulationScreenEvent.UpdateImportantWordsInput(
                                value = it
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = AppTheme.dimensions.medium)
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    VkButton(
                        onClick = {
                            viewModel.handleEvent(event = CongratulationScreenEvent.GenerateCongratulation)
                        },
                        name = stringResource(R.string.button_generate),
                        enabled = uiState.isGenerateButtonEnabled,
                        modifier = Modifier.padding(vertical = AppTheme.dimensions.small)
                    )
                }

                when (uiState.loadingState) {
                    LoadingState.Success -> {
                        PromptResultScreen(
                            message = uiState.finishedCongratulation,
                            fixProblemsInput = uiState.fixProblemsInput,
                            isButtonEnabled = uiState.isFixButtonEnabled,
                            updateChangeFixCongratulationInput = {
                                viewModel.handleEvent(
                                    event = CongratulationScreenEvent.UpdateFixProblemsInput(
                                        value = it
                                    )
                                )
                            },
                            changeCongratulation = { viewModel.handleEvent(event = CongratulationScreenEvent.FixCongratulation) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    LoadingState.Loading -> {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(AppTheme.dimensions.medium)
                        ) {
                            CircularProgressIndicator(color = AppTheme.colors.azureA100)
                        }
                    }

                    LoadingState.Error -> {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(AppTheme.dimensions.medium)
                        ) {
                            Text(text = stringResource(R.string.error_cant_generate_refresh_screen))
                        }
                    }

                    null -> {}
                }
            }

        }
    }
}

@Composable
private fun PromptParameter(
    name: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            modifier = Modifier.padding(end = AppTheme.dimensions.small)
        )
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = AppTheme.colors.transparent,
                focusedContainerColor = AppTheme.colors.transparent,
            ),
            modifier = Modifier
                .padding(bottom = AppTheme.dimensions.small)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun PromptResultScreen(
    message: String,
    fixProblemsInput: String,
    isButtonEnabled: Boolean,
    updateChangeFixCongratulationInput: (String) -> Unit,
    changeCongratulation: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val clipboardManager = LocalClipboardManager.current

    Column(modifier = modifier) {
        Card(
            Modifier.padding(AppTheme.dimensions.medium),
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.cardContainerColor,
                contentColor = AppTheme.colors.cardContentColor
            )
        ) {
            CopyableText(text = message, modifier = Modifier.padding(AppTheme.dimensions.medium))
        }
        PromptParameter(
            name = stringResource(R.string.text_fix_problems),
            value = fixProblemsInput,
            onValueChange = { updateChangeFixCongratulationInput(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.dimensions.medium)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            VkButton(
                onClick = { clipboardManager.setText(AnnotatedString(message)) },
                name = stringResource(R.string.text_copy_text),
                modifier = Modifier.padding(vertical = AppTheme.dimensions.small)
            )
            Text(
                text = stringResource(R.string.text_or),
                modifier = Modifier.padding(horizontal = AppTheme.dimensions.small)
            )
            VkOutlinedButton(
                onClick = {
                    changeCongratulation()
                },
                name = stringResource(R.string.button_fix),
                enabled = isButtonEnabled,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.small)
            )
        }
    }
}

@Composable
private fun CopyableText(
    text: String,
    modifier: Modifier = Modifier,
) {
    SelectionContainer {
        Text(
            text = text,
            modifier = modifier
        )
    }
}