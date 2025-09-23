package com.gabdanho.hapibi.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gabdanho.hapibi.R

@Composable
fun InternetLostConnectionErrorPlaceholder(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Box(modifier = modifier.verticalScroll(scrollState), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.text_connect_to_internet),
            textAlign = TextAlign.Center
        )
    }
}