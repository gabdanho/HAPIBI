package com.gabdanho.hapibi.presentation.screens.congratulation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gabdanho.hapibi.presentation.component.FriendCard
import com.gabdanho.hapibi.presentation.component.PullToRefreshContainer
import com.gabdanho.hapibi.presentation.component.VkButton
import com.gabdanho.hapibi.presentation.component.VkOutlinedButton
import com.gabdanho.hapibi.presentation.model.Friend
import com.gabdanho.hapibi.presentation.model.LoadingState
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
            onRefresh = {
                viewModel.handleEvent(
                    event = if (uiState.fixProblemsInput.isNotBlank())
                        CongratulationScreenEvent.FixCongratulation
                    else CongratulationScreenEvent.GenerateCongratulation
                )
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
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                PromptParameter(
                    name = "Какой праздник:",
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
                        .padding(horizontal = 16.dp)
                )
                PromptParameter(
                    name = "Кто для Вас этот человек:",
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
                        .padding(horizontal = 16.dp)
                )
                PromptParameter(
                    name = "Стиль поздравления:",
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
                        .padding(horizontal = 16.dp)
                )
                PromptParameter(
                    name = "Обязательные слова:",
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
                        .padding(horizontal = 16.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    VkButton(
                        onClick = {
                            viewModel.handleEvent(event = CongratulationScreenEvent.GenerateCongratulation)
                        },
                        name = "Сгенерировать",
                        enabled = uiState.isGenerateButtonEnabled,
                        modifier = Modifier.padding(vertical = 8.dp)
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
                                .padding(top = 8.dp)
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    LoadingState.Error -> {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Text(text = "Не удалось сгенерировать поздравление. Обновите страницу для повторной попытки")
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
    isError: Boolean = false,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(end = 8.dp)
        )
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = Color.Black
            ),
            trailingIcon = {
                if (isError) Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "Необходимо заполнить поле"
                )
            },
            modifier = Modifier.weight(3f)
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
            Modifier.padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            CopyableText(text = message, modifier = Modifier.padding(16.dp))
        }
        PromptParameter(
            name = "Исправить недочёты:",
            value = fixProblemsInput,
            onValueChange = { updateChangeFixCongratulationInput(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            VkButton(
                onClick = { clipboardManager.setText(AnnotatedString(message)) },
                name = "Скопировать",
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "или",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            VkOutlinedButton(
                onClick = {
                    changeCongratulation()
                },
                name = "Исправить",
                enabled = isButtonEnabled,
                modifier = Modifier.padding(vertical = 8.dp)
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