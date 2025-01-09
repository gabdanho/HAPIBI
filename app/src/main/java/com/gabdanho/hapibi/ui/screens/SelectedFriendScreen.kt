package com.gabdanho.hapibi.ui.screens


import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.gabdanho.hapibi.ui.custom.FriendCard
import com.gabdanho.hapibi.ui.custom.VkButton
import com.gabdanho.hapibi.ui.custom.VkOutlinedButton
import com.gabdanho.hapibi.ui.model.CongratPrompt
import com.gabdanho.hapibi.ui.model.UserData

@Composable
fun SelectedFriendScreen(
    modifier: Modifier = Modifier,
    userData: UserData,
    congratMessage: String,
    isGeneratingMessage: Boolean,
    generateCongrat: (CongratPrompt) -> Unit,
    changeCongratText: (String) -> Unit,
    copyCongrat: (String) -> Unit,
    clearCongratText: () -> Unit,
    backScreen: () -> Unit
) {
    CustomBackHandler {
        clearCongratText()
        backScreen()
    }
    Scaffold { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            FriendCard(user = userData, modifier = modifier.padding(top = 16.dp))
            CongratPromptField(
                firstName = userData.firstName,
                copyCongrat = copyCongrat,
                generateCongrat = generateCongrat,
                changeCongratText = changeCongratText,
                congratMessage = congratMessage,
                isGeneratingMessage = isGeneratingMessage
            )
        }
    }
}

@Composable
fun CongratPromptField(
    modifier: Modifier = Modifier,
    firstName: String,
    copyCongrat: (String) -> Unit,
    generateCongrat: (CongratPrompt) -> Unit,
    changeCongratText: (String) -> Unit,
    congratMessage: String,
    isGeneratingMessage: Boolean
) {
    val parameterNames = listOf(
        "Какой праздник:",
        "Кто для Вас этот человек:",
        "Стиль поздравления:",
        "Обязательные слова:"
    )

    var holiday by remember { mutableStateOf("") }
    var personStatus by remember { mutableStateOf("") }
    var styleCongrat by remember { mutableStateOf("") }
    var importantWords by remember { mutableStateOf("") }

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        if (congratMessage == "") {
            item {
                PromptParameter(
                    name = parameterNames[0],
                    value = holiday,
                    onValueChange = { holiday = it }
                )
            }
            item {
                PromptParameter(
                    name = parameterNames[1],
                    value = personStatus,
                    onValueChange = { personStatus = it }
                )
            }
            item {
                PromptParameter(
                    name = parameterNames[2],
                    value = styleCongrat,
                    onValueChange = { styleCongrat = it }
                )
            }
            item {
                PromptParameter(
                    name = parameterNames[3],
                    value = importantWords,
                    onValueChange = { importantWords = it }
                )
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    VkButton(
                        onClick = {
                            val congratPrompt = CongratPrompt(holiday, personStatus, styleCongrat, importantWords, firstName)
                            generateCongrat(congratPrompt)
                        },
                        name = "Сгенерировать",
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
        if (!isGeneratingMessage && congratMessage != "") {
            item {
                PromptResultScreen(
                    message = congratMessage,
                    changeCongratText = changeCongratText,
                    copyCongrat = copyCongrat
                )
            }
        }
        if (isGeneratingMessage) {
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun PromptParameter(
    modifier: Modifier = Modifier,
    name: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
            trailingIcon = { if (isError) Icon(imageVector = Icons.Filled.Warning, contentDescription = "Необходимо заполнить поле") },
            modifier = Modifier.weight(3f)
        )
    }
}

@Composable
fun PromptResultScreen(
    modifier: Modifier = Modifier,
    message: String,
    changeCongratText: (String) -> Unit,
    copyCongrat: (String) -> Unit
) {
    var fixCongrat by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Card(
            Modifier.padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(16.dp)
            )
        }
        PromptParameter(
            name = "Исправить недочёты:",
            value = fixCongrat,
            onValueChange = { fixCongrat = it }
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            VkButton(
                onClick = {
                    copyCongrat(message)
                },
                name = "Скопировать",
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "или",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            VkOutlinedButton(
                onClick = {
                      if (fixCongrat.isNotBlank()) {
                          changeCongratText(fixCongrat)
                      }
                },
                name = "Исправить",
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun CustomBackHandler(enabled: Boolean = true, onBack: () -> Unit) {
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }

    SideEffect {
        backCallback.isEnabled = enabled
    }

    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current).onBackPressedDispatcher
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, backDispatcher) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)
        onDispose { backCallback.remove() }
    }
}

//@Preview
//@Composable
//fun CongratPromptFieldPreview() {
//    SelectedFriendScreen(
//        UserData(
//            "",
//            "Bogdan",
//            "Babenko",
//            "15.06.2004"
//        )
//    )
//}