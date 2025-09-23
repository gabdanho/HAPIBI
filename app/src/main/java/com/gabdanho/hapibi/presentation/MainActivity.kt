package com.gabdanho.hapibi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gabdanho.hapibi.presentation.component.InternetLostConnectionErrorPlaceholder
import com.gabdanho.hapibi.presentation.component.LoadingPlaceholder
import com.gabdanho.hapibi.presentation.component.PullToRefreshContainer
import com.gabdanho.hapibi.presentation.model.LoadingState
import com.gabdanho.hapibi.presentation.screens.main.MainScreen
import com.gabdanho.hapibi.presentation.theme.AppTheme
import com.gabdanho.hapibi.presentation.theme.HapibiTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Основная Activity приложения Hapibi.
 *
 * Отвечает за настройку темы, обработку токена и управление состоянием загрузки.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.handleEvent(event = MainActivityEvent.RefreshToken)
        setContent {
            val uiState by viewModel.uiState.collectAsState()

            HapibiTheme {
                Surface(
                    modifier = Modifier.Companion.fillMaxSize(),
                    color = AppTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .windowInsetsPadding(WindowInsets.Companion.systemBars)
                            .windowInsetsPadding(WindowInsets.Companion.statusBars)
                            .imePadding(),
                        contentAlignment = Alignment.Companion.TopCenter,
                    ) {
                        PullToRefreshContainer(
                            enabled = uiState.loadingState is LoadingState.Error,
                            isRefreshing = uiState.loadingState is LoadingState.Loading,
                            onRefresh = { viewModel.handleEvent(event = MainActivityEvent.RefreshToken) }
                        ) {
                            when (uiState.loadingState) {
                                is LoadingState.Success -> {
                                    MainScreen()
                                }

                                is LoadingState.Error -> {
                                    InternetLostConnectionErrorPlaceholder(modifier = Modifier.fillMaxSize())
                                }

                                else -> {
                                    LoadingPlaceholder(modifier = Modifier.fillMaxSize())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}